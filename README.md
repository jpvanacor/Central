# Central

App pessoal de tarefas, lembretes, hábitos, mercado, finanças, pessoas e mais. Um único `index.html` que roda no navegador e, empacotado com Capacitor, como app Android.

- Web: https://jpvanacor.github.io/Central/
- Android: https://github.com/jpvanacor/Central/releases/latest/download/central.apk

## Agora (a tela inicial)

O Central abre como um assistente: em vez de listas, mostra uma coisa de cada vez e você só escolhe.

- **O cartão do agora:** o que fazer agora, o porquê numa linha (era às 08:00, atrasada há 2 dias, prioridade alta, não falhe duas vezes) e dois botões: **Feito** e **Depois** (daqui a 1 hora, à noite, amanhã, fim de semana, próxima semana, outra data ou algum dia; no hábito, também **Hoje não**). Tudo tem Desfazer. Tocar no título abre o detalhe.
- **A ordem:** lembrete na hora (ou que passou), hábito no horário, aniversário do dia, tarefas atrasadas e de hoje pela prioridade, hábitos do momento do dia. O que tem hora mais tarde fica numa linha **Mais tarde**; um toque nela traz para agora.
- **Ou então:** duas alternativas embaixo. O círculo conclui direto; tocar no resto traz para a frente.
- **Plano do dia** (de manhã, até as 18h, com duas tarefas ou mais): as tarefas de hoje e as atrasadas, as 5 mais urgentes já marcadas (3, 5 ou 7, em Dados). As desmarcadas vão para amanhã; a que já foi adiada 3 vezes vai para **algum dia** (a que repete só vai para amanhã e continua repetindo). Embaixo, só para saber: lembretes, hábitos e aniversários do dia.
- **Anotar:** o **+** do meio da barra (no PC, o campo **O que apareceu?**) entende sozinho o que é: `Ligar pro banco amanhã` vira tarefa, `Dentista sexta 14h` vira lembrete, `Ler 10 páginas todo dia` e `Meditar ao acordar` viram hábito, `leite, pão e café` vai para o mercado e `almoço 35 reais` vai para Finanças. `Pilates terças e quintas 7h` vira lembrete que repete toda semana, `Aniversário da mãe 12/03` repete todo ano e `Estudar 2h amanhã às 20h` guarda o 2h no nome. A linha de baixo mostra o que vai ser criado; os ícones mudam o tipo (o da carteira é **Gasto**). Tarefa sem data fica para **decidir depois** (ou escolha Hoje ou Amanhã ali mesmo). `me lembra de…`, `hábito:` e `mercado:` no começo mandam no tipo.
- **Anotar falando:** no celular, o **+** começa ouvindo. Você fala e o app anota na hora, entendendo o tipo como o campo de digitar, e o aviso diz o que virou, com Desfazer. Nas abas Tarefas, Lembretes, Hábitos, Mercado e Finanças, o que você fala entra na própria aba (em Tarefas, no projeto aberto); o gasto dito com verbo (`gastei 50 no uber`, `paguei`, `recebi`) vai para Finanças de qualquer aba. Segure o **+** para digitar; na escuta, **Digitar** leva para o campo o que já foi dito. Entende o jeito falado: `dentista sexta às 2 da tarde`, `me lembra daqui a 20 minutos de tirar o bolo`, `ligar pro João segunda que vem`, `revisar a minuta amanhã urgente`, `minuta no projeto Promotoria`, `leite pão e ovos`, `acabou o café`. "Às 7" sem dizer manhã ou noite vira a próxima vez que chega; de 1 a 6, é de tarde. No app Android usa o reconhecimento de voz do celular (pede o microfone na primeira vez); no navegador, o do Chrome. No PC, o microfone fica no campo **O que apareceu?**. Dados > Tela inicial > Anotar falando desliga.
- **Uma de cada vez:** os atalhos embaixo abrem uma folha que pergunta item por item:
  - **Para decidir:** tarefas sem data; hoje, amanhã, fim de semana, próxima semana, outra data, algum dia, já fiz ou apagar;
  - **Ficaram para trás:** o que está atrasado há mais de uma semana sai do dia (não pesa no plano nem no fechar) e espera aqui;
  - **Fechar o dia** (à noite, perto do horário do aviso): hábitos (fiz, hoje não), lembretes e tarefas que sobraram, para amanhã, outro dia, algum dia ou feito. Se passar da meia-noite no meio, continua fechando o dia que começou; o aviso da noite tocado no dia seguinte não abre o fechar de hoje;
  - **Algum dia** (no ⋮): rever o que foi guardado.
  Voltar desfaz a última escolha; Pular deixa como está.
- **Barra de baixo:** Agora, **+** e **Tudo** (todas as abas em grade). Dentro de Tarefas, Lembretes, Hábitos, Mercado e Finanças, o **+** abre o adicionar da própria aba.
- **Abas que saíram:** Tempo, Estudos e Notas não aparecem mais (nem na busca). Os dados delas continuam guardados no backup e voltam se as abas voltarem.
- **Painel antigo:** Dados > Tela inicial desliga o assistente e volta a tela de Hoje com as listas e a barra de antes.

## Tarefas

Organizada em camadas, como no TickTick:

- **Gaveta de listas** (☰ no celular; fixa ao lado no PC): Hoje, Próximos 7 dias, Todas, Feitas, etiquetas e os projetos. Projetos podem ter emoji, cor e pasta; projetos com a mesma pasta ficam juntos (por exemplo 1. Projetos, 2. Áreas). Para criar ou editar: botão **+ Projeto** ou a engrenagem no fim da gaveta.
- **A lista** mostra só o nome no topo e as tarefas em cartões que abrem e fecham. O menu ⋮ escolhe agrupar por projeto, data ou prioridade, move as atrasadas para hoje e apaga as concluídas. A data fica à direita da tarefa (vermelha quando atrasada), com ícones para descrição, subtarefas, repetição e pessoas.
- **Adicionar:** no celular, pelo + da barra de baixo; no PC, pelo campo no topo da lista. O prazo, o projeto e a prioridade podem ir junto com o título, e a linha de baixo mostra o que foi entendido antes de salvar:
  - `Ligar para o cartório sexta #promotoria !!!` cria a tarefa para sexta, no projeto Promotoria, com prioridade alta;
  - datas: `hoje`, `amanhã`, `depois de amanhã`, dias da semana (`sexta`, `na segunda`, `quinta-feira`), `dia 15`, `15/10`, `próxima semana`, `fim de semana`, `em 3 dias`;
  - `#nome` escolhe o projeto pelo começo do nome; se nenhum projeto bater, vira etiqueta;
  - `!`, `!!`, `!!!` ou `!baixa`, `!média`, `!alta` definem a prioridade;
  - os ícones da folha (prazo, prioridade, projeto, caixa do dia) fazem o mesmo sem digitar; com o campo vazio, a seta abre o detalhe completo.
- **Detalhe da tarefa:** projeto e bandeira de prioridade no topo, o círculo para concluir e a data com o atraso, título, descrição e subtarefas; embaixo, ícones para etiquetas, subtarefas, repetição e pessoas. O ⋮ tem Lembrete, Duplicar e Excluir. Salva sozinho ao fechar.

No celular, arraste a tarefa para a direita para concluir e para a esquerda para reagendar. No PC, passe o mouse na linha para reagendar, e a tecla **N** abre o adicionar. Toda mudança de prazo pode ser desfeita no aviso.

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

## Mercado

- **Só a lista:** mostra só o que você pôs para comprar desta vez. O que já foi comprado fica guardado, sem aparecer, para o app reconhecer o nome e o corredor quando você pedir de novo. Itens marcados como **sempre na lista** (o alfinete no detalhe) voltam sozinhos depois de cada compra.
- **Corredores:** 🥬 Hortifruti, 🥖 Padaria, 🥩 Carnes e peixes, 🧀 Frios e laticínios, 🥫 Mercearia, 🧊 Congelados, 🥤 Bebidas, 🧽 Limpeza e casa, 🧴 Higiene, 🐾 Pet e 🛒 Outros. O corredor é escolhido pelo nome do item (dá para trocar no detalhe, no botão da etiqueta ou com `#corredor` no texto), e a ordem dos corredores pode seguir a do seu mercado (⋮ > Ordem dos corredores).
- **Adicionar:** pelo + no celular ou pelo campo no topo no PC. Entende quantidade e vários itens de uma vez: `2 kg tomate`, `tomate 2kg`, `leite x3`, `6 ovos`, `meia dúzia de ovos`, `arroz, feijão, café`. Colar uma lista de várias linhas (do WhatsApp, por exemplo) também funciona.
- **No mercado:** o círculo (ou arrastar para a direita no celular) põe no carrinho; arrastar para a esquerda tira da lista. **Concluir compra** tira o carrinho da lista (o item fica guardado, sem aparecer) e conta mais uma compra de cada item. Tudo tem Desfazer.
- **Detalhe do item:** corredor no topo, quantidade com − e +, preço (o carrinho soma), nome, observação (marca, tamanho) e quantas vezes você já comprou. Salva sozinho ao fechar.
- **Compartilhar a lista** (⋮): manda o texto separado por corredor, pelo compartilhar do Android ou copiando no navegador.
- **Falando:** `leite pão e ovos` põe na lista; `cerveja 6` põe 6; `leite 5 reais` põe o preço no item; `comprei leite e pão` tira da lista (comprados); `deu 230 reais` lança o gasto em Finanças e conclui o carrinho; `comprei leite e pão por 25 reais` faz as duas coisas; `gastei 30 de uber` vai para Finanças sem mexer no carrinho.

## Finanças

- **Falando ou no campo O que apareceu?:** `almoço 35 reais`, `uber 18,50`, `gastei 50 no mercado`, `paguei 120 de luz ontem`, `recebi 3000 de salário`, `me pagaram 200 do freela`, `fiz um pix de 50 pra Ana`. Entende R$, reais, centavos (`18,50` ou `18 e 50`), `2 mil`, valores por extenso com "reais", a data (hoje, ontem à noite, anteontem, semana passada, mês passado, dia 5, segunda; na aba Finanças, sem "paguei" ou "gastei", `sexta` e `dia 5` são os que vêm) e a categoria pelo nome (mercado e almoço em alimentação, uber e gasolina em transporte, luz e aluguel em moradia…).
- **Parcelas e fixas:** `comprei um tênis de 300 em 3 vezes` e `parcelado em 12 meses` dividem o total (R$ 100,00 como parcela 1 de 3); `3x de 100`, `em 3 vezes de 100` e `3 parcelas de 100` já dizem a parcela. `netflix 55,90 todo mês` entra nas despesas fixas; depois, `paguei o aluguel` usa o valor da fixa e ela conta como paga no mês.
- **O que não vira lançamento:** o que é para fazer (`pagar a luz 120 reais`), o que é para depois sem gasto feito (`luz 120 reais amanhã`, `dentista dia 5 às 14h 200 reais`, `boleto de 89 até sexta`, `condomínio 850 todo dia 10`), dívida (`a Ana me deve 50 reais`) e quantidade sem preço (`comprei 2 pães` só tira o pão da lista). Na aba Finanças, o valor sozinho basta (`academia 99`); sem valor, o app pede.

## Hábitos

- **Hoje:** cada hábito numa linha com o círculo na cor dele; um toque (ou arrastar para a direita no celular) marca o dia. A lista se divide por momento do dia (Manhã, Tarde, Noite, Qualquer hora), na ordem da hora de cada um; o que foi feito desce para **Feitos** e o que não é do dia fica em **Outros dias** (fechado; dá para esconder no ⋮). O 🔥 à direita é a sequência.
- **Nunca falhe duas vezes:** uma falha isolada não quebra a sequência. Quando a última vez do hábito ficou sem marcar, a linha avisa **Não falhe duas vezes**, porque falhar de novo quebra. Marcos de 7, 21, 30, 66 e 100 dias (e outros) ganham comemoração, e o dia completo solta confete.
- **Faixa dos últimos 7 dias** no topo: o anel de cada dia enche com o que foi feito. Tocar num dia passado mostra aquele dia para marcar o que ficou para trás.
- **Adicionar:** pelo + no celular ou pelo campo no topo no PC. Dias, hora ou momento e gatilho podem ir no texto, e a linha de baixo mostra o que foi entendido:
  - `Academia seg qua sex 20h`, `Ler 10 páginas antes de dormir`, `Meditar ao acordar`, `Terço aos domingos`, `Correr de manhã dias úteis`;
  - dias: `todo dia`, `dias úteis`, `fim de semana`, `de segunda a sexta`, `seg qua sex`, `segunda e quarta`, `toda terça`, `aos sábados`;
  - hora: `20h`, `6h30`, `22:15`, `às 7`, `8 da noite`; momento: `de manhã`, `à tarde`, `à noite`, `toda manhã`;
  - gatilho: `depois de…`, `após…`, `antes de…`, `ao acordar`, `quando chegar…` (acordar vira manhã; jantar e dormir viram noite);
  - o emoji vem do nome (ler 📖, academia 🏋️, água 💧…) e a cor é a menos usada; os ícones da folha (dias e quando) fazem o mesmo sem digitar. Escrever o nome de um hábito arquivado traz ele de volta, com o histórico.
- **Detalhe:** o momento do dia (ou a hora) no topo, emoji e cor num toque, nome e os dias em botões; pelos ícones de baixo, gatilho, versão de 2 minutos e nota para a próxima vez. Embaixo, sequência, recorde, % nos últimos 30 dias, total e o calendário do mês, onde um toque marca ou desmarca qualquer dia. O ⋮ arquiva ou exclui (com Desfazer). Salva sozinho ao fechar.
- **Semana** (toque no título): grade hábito × dia, de segunda a domingo, com quantos foram feitos dos planejados e ‹ › para as semanas anteriores. Os **Arquivados** também ficam ali.
- **No Android:** o hábito com horário avisa naquela hora, se ainda faltar, com a versão de 2 minutos (ou o gatilho) e a nota; o botão **Feito** do aviso marca o hábito. Horário de madrugada (00:00 a 04:59) conta como o fim da noite: o aviso toca na madrugada seguinte e marca o dia do hábito.

## App Android

**Instalar ou atualizar.** No celular, abra o link do APK, baixe e toque no arquivo. Na primeira vez o Android pede para permitir instalação pelo Chrome. Atualizar é igual: baixe o novo e instale por cima, os dados ficam.

**Login.** Em Dados, toque em Entrar com Google. O Google não permite login dentro de apps desse tipo, então o login abre no Chrome (`app-login.html`). No fim, toque em **Abrir o Central**. Se o app não abrir, toque em **Copiar código** e cole em Dados, no campo de código. É a mesma conta do site, então os dados são os mesmos.

**Notificações** (Dados > Notificações):

| Aviso | Quando |
| --- | --- |
| Lembretes | na data e hora do lembrete; sem hora, às 09:00. Com os botões Adiar 1 h e Feito |
| Plano do dia | 07:30, com o que tem para hoje e por onde começar; tocar abre o plano (com o painel antigo: as tarefas do dia e as atrasadas) |
| Hábitos no horário | na hora marcada em cada hábito, se ainda faltar. Com o botão Feito |
| Fechar o dia | 21:00, se algo ficou em aberto; tocar abre o fechar o dia, uma coisa de cada vez (com o painel antigo: os hábitos que faltam) |
| Aniversários | 09:00 do dia |
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
