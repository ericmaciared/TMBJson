package PlannerAPI;

public class Planner {
    private RequestParameters requestParameters;
    private Plan plan;
    private Error error;
    private DebugOutput debugOutput;
    private String status;

    @Override
    public String toString() {
        return "Planner{" +
                "plan=" + plan +
                '}';
    }

    public Error getError() {
        return error;
    }

    public Plan getPlan() {
        return plan;
    }

    public RequestParameters getRequestParameters() {
        return requestParameters;
    }

    public String getStatus() {
        return status;
    }
}
