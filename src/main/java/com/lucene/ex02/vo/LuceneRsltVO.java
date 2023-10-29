package com.lucene.ex02.vo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LuceneRsltVO {
    private String cnttId;    // 컨텐츠 ID
    private String cnttTpCd;  // 컨텐츠 타입 코드
    private String title;     // 컨텐츠 타이틀
    private String ctnt;      // 컨텐츠 내용
    private String url;       // URL
    private String openYn;    // openYn
    private String useYn;     // useYn
}
