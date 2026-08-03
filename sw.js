/* Central v4 - service worker: cache do app para abrir offline e instalar como PWA */
const C = 'central-v4-1';
const CORE = ['./', './index.html', './manifest.webmanifest', './icons/icon-192.png', './icons/icon-512.png'];

self.addEventListener('install', e => {
  e.waitUntil(caches.open(C).then(c => c.addAll(CORE)).then(() => self.skipWaiting()));
});
self.addEventListener('activate', e => {
  e.waitUntil(
    caches.keys()
      .then(ks => Promise.all(ks.filter(k => k !== C).map(k => caches.delete(k))))
      .then(() => self.clients.claim())
  );
});
/* stale-while-revalidate só para arquivos do próprio site; Firebase e fontes passam direto */
self.addEventListener('fetch', e => {
  const u = new URL(e.request.url);
  if (e.request.method !== 'GET' || u.origin !== location.origin) return;
  e.respondWith(
    caches.match(e.request).then(hit => {
      const net = fetch(e.request).then(r => {
        if (r && r.ok) { const cl = r.clone(); caches.open(C).then(c => c.put(e.request, cl)); }
        return r;
      }).catch(() => hit);
      return hit || net;
    })
  );
});
