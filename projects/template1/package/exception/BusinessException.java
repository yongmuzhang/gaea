package #{package};

public class BusinessException extends RuntimeException {

    /**
     * 业务信息编码
     */
    private Integer responseCode;
    /**
     * 业务信息编码
     */
    private String responseMsg;

    /**
     * 业务信息参数
     */
    private Object[] args;


    public BusinessException() {
        super();
    }


    public BusinessException(Integer errorCode, String errorMsg) {
        this.responseCode = errorCode;
        this.responseMsg = errorMsg;
    }

    public Integer getResponseCode() { return responseCode; }
    public String getResponseMsg() { return responseMsg; }

    @Override
    public String toString() {
        return "{" + getClass().getName() + "@" + hashCode() + "[" + responseCode + "()]:" + responseMsg + "}";
    }

}
