import java.util.*;

public class DNSCache {

    class E {
        String ip;
        long exp;

        E(String ip, long ttl) {
            this.ip = ip;
            this.exp = System.currentTimeMillis() + ttl;
        }
    }

    private int cap = 1000;
    private int hit = 0;
    private int miss = 0;

    private LinkedHashMap<String, E> map =
            new LinkedHashMap<>(16, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry<String, E> e) {
                    return size() > cap;
                }
            };

    public synchronized String resolve(String d) {
        long now = System.currentTimeMillis();

        if (map.containsKey(d)) {
            E e = map.get(d);
            if (e.exp > now) {
                hit++;
                return e.ip;
            } else {
                map.remove(d);
            }
        }

        miss++;
        String ip = "172.217.14." + new Random().nextInt(255);
        map.put(d, new E(ip, 5000));
        return ip;
    }

    public String stats() {
        int total = hit + miss;
        double rate = total == 0 ? 0 : (hit * 100.0) / total;
        return "Hit Rate: " + rate + "%";
    }

    public static void main(String[] args) throws Exception {

        DNSCache d = new DNSCache();

        System.out.println(d.resolve("google.com"));
        System.out.println(d.resolve("google.com"));
        Thread.sleep(6000);
        System.out.println(d.resolve("google.com"));
        System.out.println(d.stats());
    }
}
