# CheckerFramework

This repo contains the Annoted and unannoted version of my College Assignment.

## Assignment:Implement a tree to Query data stored in text file.


To compile from terminal, following command needs to be passed : javacheck -processor org.checkerframework.checker.nullness.NullnessChecker Annoted/fileQuery/src/com/assignment/filequery/{Name of the java file}

and for index cheker

javacheck -processor org.checkerframework.checker.nullness.NullnessChecker Annoted/fileQuery/src/com/assignment/filequery/{Name of the java file}


Note : Here javacheck is an alias of $CHECKERFRAMEWORK/checker/bin/javac which is created by adding the following syntax in the ~/.bashrc file as mentioned in the manual :

export CHECKERFRAMEWORK=${HOME}/checker-framework-2.3.2 alias javacheck='$CHECKERFRAMEWORK/checker/bin/javac'


files annoted
1. Constants.java
2. FileInput.java
3. FileQuery.java
4. TreeModel.java




