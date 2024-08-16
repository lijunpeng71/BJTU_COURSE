package cn.edu.bjtu.citel.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchRangeVO implements Serializable {
    private String beginValue;
    private String endValue;
}