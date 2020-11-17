#!/bin/bash
mvn clean install
screen -dmS agregator java -jar pevent-agregator/target/PeventAgregator-1.0-SNAPSHOT.jar
screen -dmS bot java -jar pevent-bot/target/PeventBot-1.0-SNAPSHOT-jar-with-dependencies.jar