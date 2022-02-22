# Introduction
The purpose of this project is to mimic the Linux grep command. The application is used to search for strings with a specific pattern recursively in a directory, and then return the matched output to a file. 

The three inputs taken in by the application are: 
 * regex: the text string providing the search patting
 * rootPath: the root directory path
 * outFile: the name of the output file. 

The technologies used in this project are:
* Core Java
* IntelliJ Idea
* Maven
* Docker 
* GitHub

# Quick Start
Ensure you are in the Grep Project Directory
``` 
cd core_java/grep 
```
Compile and package your Java code
``` 
mvn clean compile package 
```
Launch JVM and run the app
``` 
java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp .*Romeo.*Juliet.* ./data ./out/grep.txt
```

Using Docker:
Ensure you are in the Grep Project Directory
``` 
cd core_java/grep 
```
Compile and package your Java code
``` 
mvn clean compile package 
```
Build and run the Docker app
``` 
docker build -t grep_app .
docker run --rm \
    -v `pwd`/data:/data -v `pwd`/log:/log \
    grep_app .*Romeo.*Juliet.* /data /log/grep.out 
```

#Implemenation
## Pseudocode
``` matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines) 
```
## Performance Issue
The application is constrained by the heap size. If a file is greater than the heap size and is being read by the program, an OutOfMemoryError will be thrown. A solution to this would be to increase the heap size to ensure no error is thrown. This can be solved using the -Xms command, which is used to determine the initial Java heap size. 
# Tests
Several tests were done using the Romeo and Juliet keywords within a Shakespeare piece of text. The results were then stored within an output file.
# Deployment
The GitHub repository contains the necessary files for deployment. Maven was used to package the entire project with all necessary dependencies. Additionally, a new Docker image was built locally, and the image was then pushed to Docker Hub. 
# Improvements
*	Add a feature that inverts the search. Words that do not match the given input are found and saved to an output file.
*	Add another feature that in addition to displaying the result, displays 1 line before and after the match. This would provide more context to the results.  
*	Another feature could involve displaying the line number of the result within the larger text. This would allow the user to easily find their result in the original text should they need to. 
