import java.util.*;
public class PlagiarismDetectionSystem {
    public static List<String> createNGrams(String text, int n) {
        String[] words = text.toLowerCase().split(" ");
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i <= words.length - n; i++) {
            String gram = "";
            for (int j = 0; j < n; j++) {
                gram = gram + words[i + j] + " ";
            }
            ngrams.add(gram.trim());
        }
        return ngrams;
    }
    public static void main(String[] args) {
        int n = 5;
        String doc1 = "c++ is a programming language used for software development";
        String doc2 = "c is a programming language used for web development";
        List<String> grams1 = createNGrams(doc1, n);
        List<String> grams2 = createNGrams(doc2, n);
        HashMap<String, Integer> map = new HashMap<>();
        for (String g : grams1) {
            map.put(g, 1);
        }
        int matchCount = 0;
        for (String g : grams2) {
            if (map.containsKey(g)) {
                matchCount++;
            }
        }
        double similarity = (double) matchCount / grams1.size() * 100;
        System.out.println("Matching n-grams: " + matchCount);
        System.out.println("Similarity: " + similarity + "%");
    }
}
