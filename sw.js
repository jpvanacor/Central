/* Central – service worker: network-first para HTML, cache-first para assets estáticos */
const CORE = ['./', './manifest.webmanifest', './icons/icon-192.png', './icons/icon-512.png'];

self.addEventListener('install', e => {
  e.waitUntil(self.skipWaiting());
});

self.addEventListener('activate', e => {
  /* limpa todos os caches antigos ao ativar */
  e.waitUntil(
    caches.keys()
      .then(ks => Promise.all(ks.map(k => caches.delete(k))))
      .then(() => self.clients.claim())
  );
});

self.addEventListener('fetch', e => {
  const u = new URL(e.request.url);
  if (e.request.method !== 'GET' || u.origin !== location.origin) return;

  /* index.html e a raiz: sempre busca da rede primeiro (network-first) */
  if (u.pathname.endsWith('/') || u.pathname.endsWith('.html')) {
    e.respondWith(
      fetch(e.request)
        .then(r => {
          if (r && r.ok) {
            const cl = r.clone();
            caches.open('central-net').then(c => c.put(e.request, cl));
          }
          return r;
        })
        .catch(() => caches.match(e.request))
    );
    return;
  }

  /* assets (manifest, ícones): cache-first */
  e.respondWith(
    caches.match(e.request).then(hit => {
      const net = fetch(e.request).then(r => {
        if (r && r.ok) { const cl = r.clone(); caches.open('central-assets').then(c => c.put(e.request, cl)); }
        return r;
      }).catch(() => hit);
      return hit || net;
    })
  );
});
