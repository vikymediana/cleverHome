export CLASSPATH=lib/jade.jar:lib/pi4j-core.jar:classes/

java jade.Boot -agents "Led:agents.Led;Pir:agents.Pir"
  
