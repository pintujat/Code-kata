import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Sol1 {
    public static void main(String[] args) throws IOException {
        // FileWriter class to write data from list to file
        FileWriter writer = new FileWriter("output_java.txt"); 
        // Scanner class to read data from file
        Scanner s = new Scanner(new File("wordlist.txt"));
        ArrayList<String> fileList = new ArrayList<String>();
        while (s.hasNext()){
          fileList.add(s.next());
        }
        s.close();
        // String[] list = {"by","pass","bypass","carton","car","ton","den","dry","Dryden","cart","on","carton"};
        String[] list = fileList.toArray(String[]::new);
        List<String> result = new ArrayList<>();
        // passing the list to check for the words present in the list
        result = findTheWords(list);
        // System.out.println(result);
        for(String str: result) {
            writer.write(str + System.lineSeparator());
          }
        writer.close();
    }
    
    public static List<String> findTheWords(String[] words) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (String w : words) {
            set.add(w);
        }
        int count = 0;
        for (String w : words) {
            if (w.length() == 0) {
                continue;
            }
            set.remove(w);
            // Iterating and memorisation word present in the set 
            if (dp(set, w, new HashSet<>())) {
                res.add(w);
                count++;
            }
            set.add(w);
        }
        System.out.println(count);
        return res;
    }

    static boolean dp(Set<String> set, String word, Set<String> fset) {
        if (set.contains(word)) {
            return true;
        }
        if (word.length() > 6 || word.length() < 6){
            return false;
        }
        for (int i = word.length() - 1; i > 0; i--) {
            String t = word.substring(0, i);
            if (set.contains(t)) {
                if (dp(set, word.substring(i), fset)) {
                    return true;
                }
            }
        }
        fset.add(word);
        return false;
    }
}