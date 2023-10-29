package com.lucene.ex02.vo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LuceneSearchVO {
    private String openYn;         // 공개여부
    private String useYn;          // 사용여부
    private String lastChngDtt;    // 최종변경일시
}

