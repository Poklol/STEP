// Simple Hash Table with Chaining

public class SimpleHashTable {
    static class Entry {
        String key;
        String value;
        Entry next; // For chaining
        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    private Entry[] buckets;
    private int size = 10;
    public SimpleHashTable() {
        buckets = new Entry[size];
    }
    // Simple hash function using modulo
    private int hash(String key) {
        return Math.abs(key.hashCode()) % size;
    }
    public void put(String key, String value) {
        int index = hash(key);
        Entry newEntry = new Entry(key, value);
// If bucket is empty
        if (buckets[index] == null) {
            buckets[index] = newEntry;
        } else {
// Collision: chain at front



            newEntry.next = buckets[index];
            buckets[index] = newEntry;
        }
    }
    public String get(String key) {
        int index = hash(key);
        Entry current = buckets[index];
// Traverse chain
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
    public static void main(String[] args) {
        SimpleHashTable phoneBook = new SimpleHashTable();
        phoneBook.put("Alice", "555-0101");
        phoneBook.put("Bob", "555-0102");
        phoneBook.put("Charlie", "555-0103");
        System.out.println("Alice: " + phoneBook.get("Alice"));
        System.out.println("Bob: " + phoneBook.get("Bob"));
    }
}