import java.util.*;

public class TxnSys {

    static class T {
        int id;
        int amt;
        String mer;
        long time;
        String acc;

        T(int id, int amt, String mer, long time, String acc) {
            this.id = id;
            this.amt = amt;
            this.mer = mer;
            this.time = time;
            this.acc = acc;
        }
    }

    public static List<int[]> twoSum(List<T> list, int target) {
        Map<Integer, T> map = new HashMap<>();
        List<int[]> res = new ArrayList<>();

        for (T t : list) {
            int c = target - t.amt;
            if (map.containsKey(c)) {
                res.add(new int[]{map.get(c).id, t.id});
            }
            map.put(t.amt, t);
        }
        return res;
    }

    public static List<int[]> twoSumWindow(List<T> list, int target, long win) {
        Map<Integer, T> map = new HashMap<>();
        List<int[]> res = new ArrayList<>();

        for (T t : list) {
            map.entrySet().removeIf(e -> t.time - e.getValue().time > win);

            int c = target - t.amt;
            if (map.containsKey(c)) {
                res.add(new int[]{map.get(c).id, t.id});
            }
            map.put(t.amt, t);
        }
        return res;
    }

    public static Map<String, List<String>> duplicates(List<T> list) {
        Map<String, List<String>> map = new HashMap<>();

        for (T t : list) {
            String k = t.amt + "_" + t.mer;
            map.computeIfAbsent(k, x -> new ArrayList<>()).add(t.acc);
        }

        Map<String, List<String>> res = new HashMap<>();
        for (String k : map.keySet()) {
            if (map.get(k).size() > 1) {
                res.put(k, map.get(k));
            }
        }
        return res;
    }

    public static void main(String[] args) {

        List<T> list = new ArrayList<>();

        list.add(new T(1, 500, "A", 1000, "acc1"));
        list.add(new T(2, 300, "B", 2000, "acc2"));
        list.add(new T(3, 200, "C", 2500, "acc3"));
        list.add(new T(4, 500, "A", 3000, "acc4"));

        System.out.println(twoSum(list, 500));
        System.out.println(twoSumWindow(list, 500, 2000));
        System.out.println(duplicates(list));
    }
}

