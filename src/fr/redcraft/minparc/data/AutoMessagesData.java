
package fr.redcraft.minparc.data;

import java.util.ArrayList;
import java.util.List;

public class AutoMessagesData {
    public static List<AutoMessagesData> autoMessages = new ArrayList<AutoMessagesData>();
    private int messageNum;
    private String message;
    private boolean enable;

    public AutoMessagesData(int messageNum, String message, boolean enable) {
        this.setMessageNum(messageNum);
        this.setMessage(message);
        this.setEnable(enable);
        autoMessages.add(this);
    }

    public int getMessageNum() {
        return this.messageNum;
    }

    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}

