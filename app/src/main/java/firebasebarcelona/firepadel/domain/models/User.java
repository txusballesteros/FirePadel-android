package firebasebarcelona.firepadel.domain.models;

public class User {
  private String userName;
  private String userId;
  private int state;

  public String getUserName() {
    return userName;
  }

  public String getUserId() {
    return userId;
  }

  public int getState() {
    return state;
  }

  private User(Builder builder) {
    userName = builder.userName;
    userId = builder.userId;
    state = builder.state;
  }

  public static final class Builder {
    private String userName;
    private String userId;
    private int state;

    public Builder() {
    }

    public Builder userName(String val) {
      userName = val;
      return this;
    }

    public Builder userId(String val) {
      userId = val;
      return this;
    }

    public Builder state(int val) {
      state = val;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
}
