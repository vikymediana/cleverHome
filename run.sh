export CLASSPATH=lib/jade.jar:lib/pi4j-core.jar:classes/:lib/com.mysql.jdbc_5.1.5.jar

java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=4000,suspend=n  jade.Boot -agents "Led:agents.Led;Pir:agents.Pir;Db:agents.DatabaseAgent;Fan:agents.Fan;Temperature:agents.Temperature"
  
