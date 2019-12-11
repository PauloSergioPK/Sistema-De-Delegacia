# Sistema-De-Delegacia
Sistema para gerenciar boletins de ocorrencias para a cadeira de fundamentos de bancos de dados

## Como utilizar ?
- Certifique de configurar o driver do "postgres" no projeto
- Certifique de ter o necessario instalado (java jdk, javafx(Acredito que este seja opcional),e uma IDE para executar o projeto)
- IDE utilizada no desenvolvido : IntelliJ da JetBrains : https://www.jetbrains.com/idea/
- O database padrao esta setado com nome de : "Delegacia" ,caso queira alterar, lembre-se de configurar em todos os controllers que realizam a chamada
do metodo "Conectar" para o seu banco.
- Os comandos do banco estao em "comandos.TXT"
- As credenciais de login por padrao sao "Admin" e "Admin", caso queira muda-los va no "loginController" e modifique la :)  .
- O sistema de banco usado foi o PostgreSQL
- OBS : o sistema ainda esta na versao alpha da alpha da alpha, mas as funcionalidades implementadas ja estao funcionando.


## Configurando driver no IntelliJ
- Abra o projeto

![alt text](https://i.imgur.com/lywbtlP.jpg)

- Va em File e em estrutura do projeto

![alt text](https://i.imgur.com/ILV4isP.jpg)

- Va em Libraries e adicione o driver encontrado na pasta "driver"

![alt text](https://i.imgur.com/5nAxLEl.jpg)



## Desenvolvedores :
- Paulo Sergio Rabelo Costa
- Nathan Ferreira Silva