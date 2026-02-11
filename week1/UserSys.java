import java.util.*;

public class UserSys {

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
            if (!users.containsKey(s)) {
                res.add(s);
            }
        }
        return res;
    }

    public String mostAttempted() {
        if (freq.isEmpty()) return "No attempts yet";
        return Collections.max(freq.entrySet(),
                Map.Entry.comparingByValue()).getKey();
    }

    public static void main(String[] args) {

        UserSys us = new UserSys();

        us.add("john_doe", 1);

        System.out.println(us.check("john_doe"));
        System.out.println(us.check("jane_smith"));
        System.out.println(us.suggest("john_doe"));

        us.check("admin");
        us.check("admin");
        us.check("admin");

        System.out.println(us.mostAttempted());
    }
}


