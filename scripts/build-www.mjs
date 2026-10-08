// Copia o app web (os mesmos arquivos que o GitHub Pages publica) para www/, que vai dentro do APK.
import { cpSync, mkdirSync, rmSync } from "node:fs";
const files = ["index.html", "manifest.webmanifest", "icons"];
rmSync("www", { recursive: true, force: true });
mkdirSync("www");
for (const f of files) cpSync(f, `www/${f}`, { recursive: true });
console.log("www pronto:", files.join(", "));
