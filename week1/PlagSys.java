import java.util.*;

public class PlagSys {

    private Map<String, Set<String>> map = new HashMap<>();
    private int n = 5;

    public void addDoc(String id, String txt) {
        String[] w = txt.split("\\s+");

        for (int i = 0; i <= w.length - n; i++) {
            String g = String.join(" ", Arrays.copyOfRange(w, i, i + n));
            map.computeIfAbsent(g, k -> new HashSet<>()).add(id);
        }
    }

    public double checkDoc(String id, String txt) {
        String[] w = txt.split("\\s+");
        int total = 0;
        int match = 0;

        for (int i = 0; i <= w.length - n; i++) {
            total++;
            String g = String.join(" ", Arrays.copyOfRange(w, i, i + n));
            if (map.containsKey(g) && !map.get(g).contains(id)) {
                match++;
            }
        }

        if (total == 0) return 0;
        return (match * 100.0) / total;
    }

    public static void main(String[] args) {

        PlagSys p = new PlagSys();

        String d1 = "java is a programming language used widely in software development";
        String d2 = "java is a programming language used widely in web development";

        p.addDoc("doc1", d1);

        double sim = p.checkDoc("doc2", d2);

        System.out.println("Similarity: " + sim + "%");
    }
}

