package kr.ac.shingu.soft.appfrm.data;

public class LoginData {
    private String userId;
    private String userPwd;

    public LoginData() {
    }

    // 기본 생성자
    public LoginData(String userId, String userPwd) {
        this.userId = userId;
        this.userPwd = userPwd;
    }

    // getter + setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    // 디버깅 용도의 String화
    @Override
    public String toString() {
        return "LoginData [userId=" + userId + ", userPwd=" + userPwd + "]";
    }

}
