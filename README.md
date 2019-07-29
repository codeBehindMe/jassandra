# Jassandra

A java based data stream for kerbal space program.

## Developer getting started

This project uses maven. 

Download the krpc jar file from 
https://github.com/krpc/krpc/releases/download/v0.4.8/krpc-java-0.4.8.jar

Install it to your local repository
```
 mvn  install:install-file -Dfile=krpc-java-0.4.8.jar -DgroupId=krpc.client -DartifactId=krpc -Dversion=0.4.8 -Dpackaging=jar
```

Package the project
```
mvn package 
```

Start the krpc server (see the krpc pages) and then run the package.
```
java -cp target/jassandra-1.0-SNAPSHOT-jar-with-dependencies.jar com.daedalus.jassandra.Jassandra
```