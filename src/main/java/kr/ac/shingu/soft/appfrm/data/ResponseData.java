package kr.ac.shingu.soft.appfrm.data;

public class ResponseData {
    private String msg;
    private boolean success;

    public ResponseData() {
    }

    public ResponseData(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ResponseData [msg=" + msg + ", success=" + success + "]";
    }

}
