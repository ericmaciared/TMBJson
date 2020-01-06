package PlannerAPI;

public class Error {
    private int id;
    private String message;
    private String[] missing;
    private String msg;
    private boolean noPath;

    public String getMsg() {
        return msg;
    }
}
