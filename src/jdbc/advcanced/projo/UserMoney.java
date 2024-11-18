package jdbc.advcanced.projo;

public class UserMoney {
    private Integer ID;
    private String UserName;
    private Integer Money;

    public UserMoney(Integer ID, String userName, Integer money) {
        this.ID = ID;
        UserName = userName;
        Money = money;
    }

    public UserMoney() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Integer getMoney() {
        return Money;
    }

    public void setMoney(Integer money) {
        Money = money;
    }

    @Override
    public String toString() {
        return "UserMoney{" +
                "ID=" + ID +
                ", UserName='" + UserName + '\'' +
                ", Money=" + Money +
                '}';
    }
}
