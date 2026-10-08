# Central

App pessoal de tarefas, lembretes, hábitos, tempo, finanças, estudos, pessoas e mais. Um único `index.html` que roda no navegador e, empacotado com Capacitor, como app Android.

- Web: https://jpvanacor.github.io/Central/
- Android: https://github.com/jpvanacor/Central/releases/latest/download/central.apk

## Tarefas

Organizada em camadas, como no TickTick:

- **Gaveta de listas** (☰ no celular; fixa ao lado no PC): Hoje, Próximos 7 dias, Todas, Feitas, etiquetas e os projetos. Projetos podem ter emoji, cor e pasta; projetos com a mesma pasta ficam juntos (por exemplo 1. Projetos, 2. Áreas). Para criar ou editar: botão **+ Projeto** ou a engrenagem no fim da gaveta.
- **A lista** mostra só o nome no topo e as tarefas em cartões que abrem e fecham. O menu ⋮ escolhe agrupar por projeto, data ou prioridade, move as atrasadas para hoje e apaga as concluídas. A data fica à direita da tarefa (vermelha quando atrasada), com ícones para descrição, subtarefas, repetição e pessoas.
- **Adicionar:** no celular, pelo botão + flutuante; no PC, pelo campo no topo da lista. O prazo, o projeto e a prioridade podem ir junto com o título, e a linha de baixo mostra o que foi entendido antes de salvar:
  - `Ligar para o cartório sexta #promotoria !!!` cria a tarefa para sexta, no projeto Promotoria, com prioridade alta;
  - datas: `hoje`, `amanhã`, `depois de amanhã`, dias da semana (`sexta`, `na segunda`, `quinta-feira`), `dia 15`, `15/10`, `próxima semana`, `fim de semana`, `em 3 dias`;
  - `#nome` escolhe o projeto pelo começo do nome; se nenhum projeto bater, vira etiqueta;
  - `!`, `!!`, `!!!` ou `!baixa`, `!média`, `!alta` definem a prioridade;
  - os ícones da folha (prazo, prioridade, projeto, caixa do dia) fazem o mesmo sem digitar; com o campo vazio, a seta abre o detalhe completo.
- **Detalhe da tarefa:** projeto e bandeira de prioridade no topo, o círculo para concluir e a data com o atraso, título, descrição e subtarefas; embaixo, ícones para etiquetas, subtarefas, repetição, pessoas e foco. O ⋮ tem Focar, Lembrete, Duplicar e Excluir. Salva sozinho ao fechar.

No celular, arraste a tarefa para a direita para concluir e para a esquerda para reagendar. No PC, passe o mouse na linha para focar ou reagendar, e a tecla **N** abre o adicionar. Toda mudança de prazo pode ser desfeita no aviso.

## Lembretes

Mesma estrutura das Tarefas, pensada como agenda:

- **Agenda:** cartões por dia (Atrasados, Hoje, Amanhã, os próximos dias e depois por mês), com a hora numa coluna à esquerda. No cartão de hoje, uma linha vermelha marca o agora e anda sozinha; o que já passou fica apagado. Os aniversários de Pessoas aparecem no dia (dá para tirar no ⋮).
- **Gaveta** (☰ no celular; fixa ao lado no PC): Agenda, Sem data, Aniversários, Dispensados e as categorias. Na categoria, o ⋮ renomeia.
- **Adicionar:** pelo + no celular ou pelo campo no topo no PC. A data, a hora, a repetição e a categoria podem ir no texto, e a linha de baixo mostra o que foi entendido:
  - `Remédio todo dia 8h`, `Reunião toda segunda 9h30 #trabalho`, `Pagar aluguel todo dia 5`, `Ligar pro banco amanhã de manhã`, `Dentista 15/10 às 14:30`;
  - hora: `8h`, `9h30`, `14:30`, `às 10`, `meio-dia`, `de manhã` (09:00), `à tarde` (15:00), `à noite` (20:00);
  - repetição: `todo dia`, `dias úteis`, `de segunda a sexta`, `toda semana`, `toda segunda`, `todo dia 5`, `todo mês`, `todo ano`, `a cada 3 dias`, `a cada 2 semanas`;
  - datas como nas tarefas; `#nome` é a categoria;
  - sem data no texto, o que repete começa na próxima vez possível e o que só tem hora fica para hoje (ou amanhã, se a hora já passou);
  - os ícones da folha (data, hora, repetição, categoria) fazem o mesmo sem digitar.
- **Detalhe:** categoria no topo, o círculo para dispensar, data e hora em botões, título e descrição; embaixo, repetição, pessoas e adiar. O ⋮ tem Adiar, Tarefa, Duplicar e Excluir. Salva sozinho ao fechar. No app, diz quando o aviso vai tocar.
- **Dispensar e adiar:** o círculo (ou arrastar para a direita no celular) dispensa; o que repete pula para a próxima vez. Arrastar para a esquerda (ou o relógio) abre o adiar: em 1 hora, hoje à noite, amanhã cedo, fim de semana, próxima semana ou outra data. No que repete, só aquela vez muda e a repetição continua. Tudo tem Desfazer.
- **No aviso do Android:** os botões **Adiar 1 h** e **Feito** resolvem sem abrir a lista, e tocar no aviso abre o lembrete.

## App Android

**Instalar ou atualizar.** No celular, abra o link do APK, baixe e toque no arquivo. Na primeira vez o Android pede para permitir instalação pelo Chrome. Atualizar é igual: baixe o novo e instale por cima, os dados ficam.

**Login.** Em Dados, toque em Entrar com Google. O Google não permite login dentro de apps desse tipo, então o login abre no Chrome (`app-login.html`). No fim, toque em **Abrir o Central**. Se o app não abrir, toque em **Copiar código** e cole em Dados, no campo de código. É a mesma conta do site, então os dados são os mesmos.

**Notificações** (Dados > Notificações):

| Aviso | Quando |
| --- | --- |
| Lembretes | na data e hora do lembrete; sem hora, às 09:00. Com os botões Adiar 1 h e Feito |
| Tarefas do dia | 07:30, com as tarefas do dia e as atrasadas |
| Hábitos que faltam | 21:00, só se ainda faltar algum |
| Aniversários | 09:00 do dia |
| Pomodoro | no fim de cada foco e de cada pausa |
| Sessão em andamento | aviso fixo com o tempo correndo: regressivo no Pomodoro (some no fim da fase), progressivo no cronômetro; tocar abre o Tempo |
| Blocos da rotina | desligado, para não repetir o Orders of the Day |

Os avisos são recalculados sempre que o app abre ou algo muda nele. Cada tipo tem um canal próprio no Android, com som próprio, que dá para ajustar nas configurações de notificação do sistema.

**Sons, vibração e animações** (Dados > Sons e animações): som e vibração ao concluir tarefa, hábito e lembrete, confete quando as tarefas do dia ou os hábitos fecham, transições entre abas.

**Backup no app.** Exportar backup abre o menu de compartilhar do Android (Drive, Arquivos, e-mail).

## Como o APK é gerado

O workflow `.github/workflows/android.yml` roda a cada push em `main` que mexe no app:

1. copia `index.html`, `manifest.webmanifest` e `icons/` para `www/` (`scripts/build-www.mjs`);
2. `npx cap sync android` leva o app web e os plugins para `android/`;
3. o Gradle gera o APK assinado;
4. o APK é publicado como `central.apk` na release `vN`, onde N é `const APP_VERSION` do `index.html`, com "build X" nas notas.

O app consulta a última release e mostra **Nova versão do app pronta** quando o build publicado é mais novo que o instalado. Para abrir uma release nova (v2, v3…), aumente `APP_VERSION` no `index.html`.

A chave de assinatura (`android/app/central-release.p12`) fica no repositório para que cada build atualize o app instalado sem desinstalar. Como o repositório é público, qualquer pessoa pode assinar um APK com ela: instale só o APK baixado deste repositório.

## Estrutura

| Arquivo | Para quê |
| --- | --- |
| `index.html` | o app inteiro (web e Android) |
| `sw.js`, `manifest.webmanifest`, `icons/` | instalação como PWA no navegador |
| `app-login.html` | login do Google para o app Android, aberto no Chrome |
| `capacitor.config.json`, `android/` | projeto Android (Capacitor 8) |
| `android/app/src/main/java/.../FocoPlugin.java` | aviso fixo da sessão, com contagem no próprio aviso |
| `android/app/src/main/res/raw/` | sons das notificações |
| `package.json` | Capacitor e plugins (notificações locais, vibração, app, abrir links, arquivos, compartilhar) |
