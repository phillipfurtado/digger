#!/bin/bash          
echo Instalacao Digger
  
echo Instalando bibliotecas
sudo apt-get install -y --force-yes openjdk-8-jdk curl wget nodejs 

echo Instalando Docker
sudo curl -fsSL https://get.docker.com/gpg | sudo apt-key add -

sudo wget -nc --directory-prefix=/tmp http://mirror.nbtelecom.com.br/apache/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.zip
sudo unzip -o /tmp/apache-maven-3.3.9-bin.zip -d /tmp
/tmp/apache-maven-3.3.9/bin/mvn clean install

found_container=$(docker ps -a | grep "digger-mongo")
if [ ! -z "$found_container" ]; then
  sudo docker start "digger-mongo"
else
  docker run --name digger-mongo -d mongo
fi

echo Container mongodb iniciado. Se aplicacao nao se comunicar com o mongo verifique o ip do container com o comando: \"docker inspect --format \'{{ .NetworkSettings.IPAddress }}\' digger-mongo\" e atualize o arquivo ServidorService.java 
