public class UserData {
    private String email;
    private String password;
    private String name;

    public UserData (String email, String password, String name ) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public UserData() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
