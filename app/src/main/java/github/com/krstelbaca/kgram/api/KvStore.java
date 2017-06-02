package github.com.krstelbaca.kgram.api;

import java.util.HashMap;
import java.util.List;

/**
 * @author krystel
 * @since 6/1/17.
 */

public class KvStore {
    private static HashMap<String, List<Message>> store;

    public static void add(String key, Message message) {
        store.get(key).add(message);
    }

    public static List<Message> get(String key) {
        return store.get(key);
    }

    public static User krystel = new User("Krystel");

}
