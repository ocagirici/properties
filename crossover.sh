 #!/bin/bash

mvn clean package exec:java -Dexec.mainClass="com.crossover.trial.properties.Main" -Dexec.args="classpath:home/onr/Desktop/jdbc.properties file:///home/onr/Desktop/aws.properties http://homes.ieu.edu.tr/ocagirici/config.json" > output.txt
