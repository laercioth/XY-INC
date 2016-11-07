# xy-inc

**1. PRÉ-REQUISITOS**<br/>

Será demonstrado a execução/configuração do projeto apenas no ambiente Linux (Mint 17).

Ferramentas necessárias para realizar os testes!

JDK 8: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html<br/>
Wildfly-10.1.0.Final: http://wildfly.org/downloads/<br/>
Maven: http://maven.apache.org/download.cgi<br/>
Banco de dados mysql e seu cliente de consultas<br/>
```
sudo apt-get update && sudo apt-get upgrade

sudo apt-get install mysql-server-5.7

sudo apt-get install mysql-workbench

```
<br/>
Crie no mysql-workbench um schema com o nome de XY-INC<br/>

Logo após, baixe e execute o script com o nome de schema.sql<br/>

Ele irá criar a tabela POI para realizar o armazenamento das informações.<br/>

**2. ARQUITETURA PROPOSTA**<br/>
A arquitetura proposta é composta de três camadas principais:


•	RESTful - Fachada de atendimento de todas as solicitações. Foi utilizado a biblioteca RESTEasy que é um projeto da Jboss.

•	Objetos de negócio (EJB) - EJB fornece uma forma simples e rápida para desenvolvimento de aplicações Java e oferece controle de transações;

•	Persistência - Foi utilizado JPA/Hibernate;

<br/>

**3. CONFIGURAÇÕES**<br/>
Para instalar o servidor de aplicação **wildfly** será necessário baixar a versão **10.1.0.Final** do servidor e descompactar em um local de sua preferência. <br/>
Logo após, seguir os passos abaixo para configuração do servidor.<br/>

**3.1. Instalar Driver MySql no servidor Wildfly**<br/>
Acesse o diretório $WILDFLY_HOME/modules/system/layers/base/com<br/>
Crie o diretório **mysql** e seu subdiretório **main**<br/>
Dentro da pasta **main** baixe e extraia o jar de conexão do mysql. **mysql-connector-java-5.1.39-bin.jar**  `https://downloads.mysql.com/archives/c-j/` <br/>
Crie um arquivo chamado module.xml e dentro dele adicione as seguintes linhas <br/>
```
<?xml version="1.0" encoding="UTF-8"?>
 
<module xmlns="urn:jboss:module:1.1" name="com.mysql">
    <resources>
        <!-- Deve ser o mesmo nome do jar baixado-->
        <resource-root path="mysql-connector-java-5.1.39-bin.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
        <module name="javax.servlet.api" optional="true"/>
    </dependencies>
</module>
```

Abra o arquivo **JBOSS_HOME/standalone/configuration/standalone.xml** para edição e acrescente a seguinte configuração dentro da tag `<drivers>`:
```
<driver name="mysql" module="com.mysql">
    <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
</driver>

```

**3.2. Configuração do DataSource JNDI no servidor Wildfly**<br/>
Abra o arquivo **JBOSS_HOME/standalone/configuration/standalone.xml** para edição e acrescente a seguinte configuração dentro da tag `<datasources>:`

```
<datasource jta="true" jndi-name="java:jboss/datasources/XY-INC" pool-name="XY-INC" enabled="true" use-java-context="true" use-ccm="true">
    <connection-url>jdbc:mysql://localhost:3306/XY-INC</connection-url>
    <driver>mysql</driver>
    <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
    <pool>
        <min-pool-size>10</min-pool-size>
        <max-pool-size>100</max-pool-size>
        <prefill>true</prefill>
    </pool>
    <security>
        <user-name>root</user-name>
        <password>root</password>
    </security>
    <statement>
        <prepared-statement-cache-size>32</prepared-statement-cache-size>
        <share-prepared-statements>true</share-prepared-statements>
    </statement>
</datasource>
```
<br/>

**4. BUILD E DEPLOY**

**4.1. Build**
Dentro da pasta xy-inc, execute:<br/>
mvn clean install<br/>

**4.2. Deploy**
Copiar o arquivo _**xy-inc/xy-inc-ear/target/xy-inc-ear-1.0.0.ear**_ para a pasta **_JBOSS_HOME\standalone\deployments_**<br/>

**4.3. Start Server**<br/>
Para subir o servidor de aplicações Wildfly será necessário entrar na pasta JBOSS_HOME\bin e executar o seguinte comando:<br/>
No Linux: ./standalone.sh<br/>

<br/>
**5 TESTES**<br/>

Testar recursos REST nas seguintes urls:  <br/>

GET `http://localhost:8080/xy-inc-apiRest/service/pois` 				- Lista todos os POI <br/>
GET `http://localhost:8080/xy-inc-apiRest/service/pois/{x}/{y}/{range}` - Busca POI no raio informado<br/>
POST `http://localhost:8080/xy-inc-apiRest/service/pois` 				- Cria um novo POI


<br/>
