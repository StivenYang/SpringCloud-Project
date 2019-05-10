package top.hengshare.blog.utils;

import top.hengshare.blog.utils.constant.ReturnCode;

/**
 * Created by StivenYang
 * Date 2017/11/10
 * Description:接口统一返回格式
 */
public class ResponseEntityUtil {

    /**是否成功*/
    private boolean success;
    /**返回码*/
    private String code;
    /**返回信息*/
    private String msg;
    /**返回数据*/
    private Object data;


    /**
     * 自定义返回结果
     * 建议使用统一的返回结果，特殊情况可以使用此方法
     * @param success
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static ResponseEntityUtil msg(boolean success,String code,String msg,String data){
        ResponseEntityUtil entityUtil = new ResponseEntityUtil();
        entityUtil.setSuccess(success);
        entityUtil.setCode(code);
        entityUtil.setMsg(msg);
        entityUtil.setData(data);
        return entityUtil;
    }

    /**
     * 查询失败
     * @return
     */
    public static ResponseEntityUtil error(){
        ResponseEntityUtil entityUtil = new ResponseEntityUtil();
        entityUtil.setSuccess(false);
        entityUtil.setCode(ReturnCode.FEAILED.getCode());
        entityUtil.setMsg(ReturnCode.FEAILED.getMsg());
        entityUtil.setData(null);
        return entityUtil;
    }

    /**
     * 查询成功但无数据
     * @return
     */
    public static ResponseEntityUtil success(){
        ResponseEntityUtil entityUtil  = new ResponseEntityUtil();
        entityUtil.setSuccess(true);
        entityUtil.setCode(ReturnCode.NODATA.getCode());
        entityUtil.setMsg(ReturnCode.NODATA.getMsg());
        entityUtil.setData(null);
        return entityUtil;
    }

    /**
     * 查询成功且有数据
     * @param data
     * @return
     */
    public static ResponseEntityUtil success(Object data){
        ResponseEntityUtil entityUtil = new ResponseEntityUtil();
        entityUtil.setSuccess(true);
        entityUtil.setCode(ReturnCode.SUCCESS.getCode());
        entityUtil.setMsg(ReturnCode.SUCCESS.getMsg());
        entityUtil.setData(data);
        return  entityUtil;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResponseWrapper{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
