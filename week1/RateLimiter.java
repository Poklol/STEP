import java.util.concurrent.*;

public class RateLimiter {

    class B {
        int tok;
        long last;
        int max = 1000;
        long win = 3600000;

        B() {
            tok = max;
            last = System.currentTimeMillis();
        }
    }

    private ConcurrentHashMap<String, B> map = new ConcurrentHashMap<>();

    public synchronized String allow(String id) {
        long now = System.currentTimeMillis();
        B b = map.computeIfAbsent(id, k -> new B());

        if (now - b.last >= b.win) {
            b.tok = b.max;
            b.last = now;
        }

        if (b.tok > 0) {
            b.tok--;
            return "Allowed (" + b.tok + " remaining)";
        } else {
            long retry = (b.win - (now - b.last)) / 1000;
            return "Denied (retry after " + retry + "s)";
        }
    }

    public static void main(String[] args) {

        RateLimiter r = new RateLimiter();

        for (int i = 0; i < 5; i++) {
            System.out.println(r.allow("abc123"));
        }
    }
}
