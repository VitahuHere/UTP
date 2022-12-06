/**
 *
 *  @author Vu Cong Minh S25206
 *
 */

package UTP5.zad1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
    private ArrayList<String> data;

    public Finder(String path){
        this.data = new ArrayList<>();
        try {
            data.addAll(Files.readAllLines(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        data = processData(data);
    }

    private ArrayList<String> processData(ArrayList<String> data){
        ArrayList<String> processed = removeWhiteSpace(data);
        processed = removeComments(processed);
        processed = removeIfsInString(processed);
        return processed;
    }

    private ArrayList<String> removeWhiteSpace(ArrayList<String> data){
        ArrayList<String> result = new ArrayList<>();
        String temp;
        for (String line : data) {
            temp = line.trim();
            if (!temp.isEmpty()) {
                result.add(temp);
            }
        }
        return result;
    }

    private ArrayList<String> removeComments(ArrayList<String> data){
        ArrayList<String> result = new ArrayList<>();
        int commentLevel = 0;
        for (String line : data) {
            if (line.startsWith("//")) {
                continue;
            }
            if (line.startsWith("/*")) {
                commentLevel++;
                continue;
            }
            if (line.endsWith("*/")) {
                commentLevel--;
                continue;
            }
            if (commentLevel == 0) {
                result.add(line);
            }
        }
        return result;
    }

    private ArrayList<String> removeIfsInString(ArrayList<String> data){
        ArrayList<String> result = new ArrayList<>();
        boolean inQuote = false;
        for (String line : data) {
            StringBuilder temp = new StringBuilder();
            for(char c : line.toCharArray()){
                if(c == '"'){
                    inQuote = !inQuote;
                }
                if(!inQuote){
                    temp.append(c);
                }
            }
            result.add(temp.toString());
        }
        return result;
    }

    public int getIfCount(){
        int count = 0;
        Pattern pattern = Pattern.compile("if\\s*\\(.*?\\)");
        Matcher matcher;
        for (String line : data) {
            matcher = pattern.matcher(line);
            while(matcher.find()){
                count++;
                line = line.substring(matcher.end());
                matcher = pattern.matcher(line);
            }
        }
        return count;
    }

    public int getStringCount(String str){
        int count = 0;
        Pattern pattern = Pattern.compile(str);
        for (String line : data) {
            while(pattern.matcher(line).find()) {
                count++;
                line = line.replaceFirst(str, "");
            }
        }
        return count;
    }
}    
