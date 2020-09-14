package com.thoughtworks.basic;

/**
 * description: FrequencyWord <br>
 * date: 2020/9/14/014 11:06 <br>
 *
 * @author: LouWei <br>
 * version: 1.0 <br>
 */
public class FrequencyWord {
    String word;
    Integer count;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public FrequencyWord(String word, Integer count) {
        this.word = word;
        this.count = count;
    }
}
