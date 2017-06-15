package com.burntcarlabs.greawa;

/**
 * Created by Harshraj on 26-11-2016.
 */

public class Essays {

    private String topic;
    private String answer;
    private int essayType;

    public Essays(int essayType,String topic, String answer) {
        this.topic = topic;
        this.answer = answer;
        this.essayType = essayType;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getEssayType() {
        return essayType;
    }

    public void setEssayType(int essayType) {
        this.essayType = essayType;
    }
}
