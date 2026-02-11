import java.util.*;

public class AutoSys {

    class T {
        Map<Character, T> ch = new HashMap<>();
        int f = 0;
    }

    private T root = new T();
    private Map<String, Integer> freq = new HashMap<>();

    public void add(String s) {
        freq.put(s, freq.getOrDefault(s, 0) + 1);

        T cur = root;
        for (char c : s.toCharArray()) {
            cur = cur.ch.computeIfAbsent(c, k -> new T());
        }
        cur.f = freq.get(s);
    }

    public List<String> search(String pre) {
        T cur = root;

        for (char c : pre.toCharArray()) {
            if (!cur.ch.containsKey(c)) return new ArrayList<>();
            cur = cur.ch.get(c);
        }

        List<String> res = new ArrayList<>();
        dfs(cur, pre, res);

        res.sort((a, b) -> freq.get(b) - freq.get(a));

        return res.size() > 10 ? res.subList(0, 10) : res;
    }

    private void dfs(T node, String path, List<String> res) {
        if (node.f > 0) res.add(path);

        for (char c : node.ch.keySet()) {
            dfs(node.ch.get(c), path + c, res);
        }
    }

    public static void main(String[] args) {

        AutoSys a = new AutoSys();

        a.add("java");
        a.add("javascript");
        a.add("java tutorial");
        a.add("java");
        a.add("java download");

        System.out.println(a.search("jav"));
    }
}
