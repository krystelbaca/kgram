package github.com.krstelbaca.kgram.api;

/**
 * @author krystel
 * @since 6/1/17.
 */

public class Message {
    private String message;
    private User poster;

    public Message(String message, User poster) {
        this.message = message;
        this.poster = poster;
    }

    public Message(String message) {
        this.message = message;
        this.poster = KvStore.krystel;
    }

    public String getMessage() {
        return message;
    }

    public User getPoster() {
        return poster;
    }
}
