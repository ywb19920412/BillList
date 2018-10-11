package com.example.yangwb.studyalipaybill;

import java.util.List;

/**
 * @desc:
 * @author: yangwb
 * @date: 2018/10/11 14:03
 */

public class Bill {
    private String type;
    private List<String> list;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Bill(String type, List<String> list) {
        this.type = type;
        this.list = list;
    }
}
