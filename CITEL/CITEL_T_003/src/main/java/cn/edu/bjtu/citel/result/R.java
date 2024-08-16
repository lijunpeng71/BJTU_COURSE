package cn.edu.bjtu.citel.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author junpeng.li
 * @Description
 * @ date created in 2024-08-04 10:59
 */
@Data
@AllArgsConstructor
public class R<T> implements Serializable {

    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MESSAGE = "成功";

    /**
     * 返回码 0：正确返回
     */
    private int code;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 返回成功消息
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> success(T data) {
        return new R<>(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    /**
     * 返回失败消息
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> R<T> error(int code, String message) {
        return new R<>(code, message, null);
    }
}
