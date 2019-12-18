package DataModel;

public class User {
    private String username;
    private String email;
    private int birthYear;

    public User(String username, String email, int birthYear) {
        this.username = username;
        this.email = email;
        this.birthYear = birthYear;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
