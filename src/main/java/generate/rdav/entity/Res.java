package generate.rdav.entity;

public class Res<T> {
    private String message;
    private boolean success ;
    private T data;

    //======成功时调用========
    public static  <T> Res<T> success(T data){
        return new Res<T>(data);
    }
    public static  <A> Res<A> success(String msg,Boolean b,A data){
        return new Res<A>(msg,b,data);
    }
    public static  <T> Res<T> success(String msg,Boolean b){
        return new Res<T>(msg,b);
    }
    public static  <T> Res<T> success(String msg,T data){
        return new Res<T>(msg,data);
    }
    //=========失败调用=====
    public static <T> Res<T> fail(String  msg){
        return new Res<T>(msg);
    }
    //=====构造方法==========
    public Res(T data){
        this.setMessage("操作成功！");
        this.setSuccess(true);
        this.setData(data);
    }
    public Res(String msg,Boolean b,T data){
        this.setMessage(msg);
        this.setSuccess(b);
        this.setData(data);
    }
    public Res(String msg,T data){
        this.setMessage(msg);
        this.setSuccess(true);
        this.setData(data);
    }
    public Res(String msg,Boolean b){
        this.setMessage(msg);
        this.setSuccess(b);
        this.setData(data);
    }
    public Res(String msg){
        this.setMessage(msg);
        this.setSuccess(false);
        this.setData(null);
    }
    //========get/set===========
    public T getData(){
        return this.data;
    }
    public String getMessage() {
        return this.message;
    }
    public boolean getSuccess(){
        return this.success;
    }


    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
