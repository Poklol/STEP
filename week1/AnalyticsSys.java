import java.util.*;

public class AnalyticsSys {

    private Map<String, Integer> page = new HashMap<>();
    private Map<String, Set<String>> uniq = new HashMap<>();
    private Map<String, Integer> src = new HashMap<>();

    public void add(String url, String user, String s) {
        page.put(url, page.getOrDefault(url, 0) + 1);
        uniq.computeIfAbsent(url, k -> new HashSet<>()).add(user);
        src.put(s, src.getOrDefault(s, 0) + 1);
    }

    public List<String> top() {
        PriorityQueue<String> pq =
                new PriorityQueue<>((a, b) -> page.get(a) - page.get(b));

        for (String k : page.keySet()) {
            pq.add(k);
            if (pq.size() > 10) pq.poll();
        }

        List<String> res = new ArrayList<>(pq);
        Collections.reverse(res);
        return res;
    }

    public void dash() {
        System.out.println("Top Pages:");
        for (String s : top()) {
            System.out.println(s + " - " + page.get(s)
                    + " views (" + uniq.get(s).size() + " unique)");
        }

        int total = src.values().stream().mapToInt(i -> i).sum();

        System.out.println("\nTraffic Sources:");
        for (String s : src.keySet()) {
            double p = (src.get(s) * 100.0) / total;
            System.out.println(s + ": " + String.format("%.1f", p) + "%");
        }
    }

    public static void main(String[] args) {

        AnalyticsSys a = new AnalyticsSys();

        a.add("/article/breaking-news", "u1", "google");
        a.add("/article/breaking-news", "u2", "facebook");
        a.add("/sports/championship", "u3", "google");
        a.add("/sports/championship", "u4", "direct");
        a.add("/sports/championship", "u3", "google");

        a.dash();
    }
}

