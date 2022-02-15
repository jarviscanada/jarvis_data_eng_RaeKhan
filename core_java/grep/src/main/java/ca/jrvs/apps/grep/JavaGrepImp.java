package ca.jrvs.apps.grep;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.log4j.BasicConfigurator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaGrepImp implements JavaGrep {


    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        // Use default logger config
        BasicConfigurator.configure();

//      Test Arguments: .*Romeo.*Juliet.* /home/centos/dev/jarvis_data_eng_Rae/core_java/grep/data/txt testOutFile.txt
        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try {
            javaGrepImp.process();
        } catch (Exception ex) {
            javaGrepImp.logger.error("Error: Unable to process", ex);
        }

    }

    @Override
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<>();
        List<File> files = listFiles(rootPath);
        for (File file: files) {
            List<String> lines = readLines(file);
            for (String line: lines) {
                if (containsPattern(line)) {
                    matchedLines.add(line);
                }
            }
        }

        writeToFile(matchedLines);
    }

    /**
     *
     * @param rootDir input directory -> rootPath
     * @return
     */
    @Override
    public List<File> listFiles(String rootDir) {
        List<File> files = new ArrayList<>();
//        use rootDir as File
        File filesList = new File(rootDir);
        File[] rootDirFiles = filesList.listFiles();

        if (rootDirFiles != null) {
            for (File file:rootDirFiles) {
                if (file.isFile()){
                    files.add(file);
                }
                 else if (file.isDirectory()){
//                     call function again - recursively, if directory
                    List<File> additionalFiles = listFiles(file.getAbsolutePath());
                     files.addAll(additionalFiles);
                }
            }
        }

        return files;
    }

    @Override
    public List<String> readLines(File inputFile) throws FileNotFoundException {
        Scanner sc = new Scanner(inputFile);
        List <String> lines = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            lines.add(line);
        }

        sc.close();
        return lines;
    }

    @Override
    public boolean containsPattern(String line) {
        return line.matches(regex);
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        File fileToWriteTo = new File(this.outFile);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWriteTo));

        for (String line:lines){
            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }

    @Override
    public String getRootPath() {
        return this.rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getRegex() {
        return this.regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getOutFile() {
        return this.outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }


}
