import java.util.*;

class UserSys {

    private Map<String, Integer> users = new HashMap<>();
    private Map<String, Integer> freq = new HashMap<>();

    public boolean check(String u) {
        freq.put(u, freq.getOrDefault(u, 0) + 1);
        return !users.containsKey(u);
    }

    public void add(String u, int id) {
        users.put(u, id);
    }

    public List<String> suggest(String u) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            String s = u + i;
            if (!users.containsKey(s)) res.add(s);
        }
        return res;
    }

    public String mostAttempted() {
        return Collections.max(freq.entrySet(),
                Map.Entry.comparingByValue()).getKey();
    }
}

