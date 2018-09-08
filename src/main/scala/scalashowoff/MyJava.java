package scalashowoff;

import java.util.*;

public class MyJava {
    private String msg;
    private final long createTime;
    private final List<String> msgs;

    public MyJava(String msg) {
        this(msg, System.currentTimeMillis());
    }


    public MyJava(String msg, long createTime) {
        this.msg = msg;
        this.msgs = new ArrayList<>();
        this.msgs.add(msg);
        this.createTime = createTime;
    }


    public String getMsg() {
        System.out.println("Getting message");
        return msg;
    }

    public void setMsg(String msg) {
        this.msgs.add(msg);
        this.msg = msg;
    }

    public long getCreateTime() {
        return createTime;
    }

    public Set<String> getMsgs() {
        return new HashSet<>(msgs);
    }
}
