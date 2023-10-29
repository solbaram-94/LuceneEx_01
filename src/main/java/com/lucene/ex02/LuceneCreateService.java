//package com.lucene.ex02;
//
//import com.lge.wee.mvc.common.BaseService;
//import com.lge.wee.mvc.common.util.StringUtil;
//import com.lge.wee.mvc.lucene.document.vo.LuceneRsltVO;
//import com.lge.wee.mvc.lucene.document.vo.LuceneSearchVO;
//import com.lucene.ex02.vo.LuceneRsltVO;
//import com.lucene.ex02.vo.LuceneSearchVO;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.StringField;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.store.FSDirectory;
//import org.springframework.beans.factory.annotation.Value;
////import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@RequiredArgsConstructor
////@Service
////public class LuceneCreateService extends BaseService {
//public class LuceneCreateService {
//
//    private final LuceneMapper luceneMapper;
//
//    // 서버 적용 todo 실제 서버 적용 위치 확인 필요
//    @Value("${mvc.lucene.luceneIndexFilesDir}")
//    private String INDEX_DIR;
//
//    /**
//     * 컨테츠 색인 생성
//     * @throws Exception
//     */
//    public int createIndex() throws Exception {
//// 대상 데이터 조회
//        LuceneSearchVO searchVO = new LuceneSearchVO();
////        List<LuceneRsltVO> dataList = luceneMapper.selectTechDocList(searchVO);
//
//        long startTime = System.currentTimeMillis();
//
//// document 생성 : row 별 (1 row 당 문서 1개)
//        List<Document> docList = this.makeDocList(dataList);
//        log.debug("CREATE :: make docList (m)[{}]", (System.currentTimeMillis() - startTime)/1000);
//        startTime = System.currentTimeMillis();
//
//// indexWriter 생성
//        IndexWriter indexWriter = this.createWriter();
//        log.debug("CREATE :: make IndexWriter (m)[{}]", (System.currentTimeMillis() - startTime)/1000);
//        startTime = System.currentTimeMillis();
//
//// 기존 정보 모두 삭제
//        indexWriter.deleteAll();
//        log.debug("CREATE :: delete index (m)[{}]", (System.currentTimeMillis() - startTime)/1000);
//        startTime = System.currentTimeMillis();
//
//// 인덱스 생성
//        indexWriter.addDocuments(docList);
//        log.debug("CREATE :: make index (m)[{}]", (System.currentTimeMillis() - startTime)/1000);
//        startTime = System.currentTimeMillis();
//
//        indexWriter.commit();
//        log.debug("CREATE :: commit (m)[{}]", (System.currentTimeMillis() - startTime)/1000);
//        startTime = System.currentTimeMillis();
//
//        indexWriter.close();
//        log.debug("CREATE :: close (m)[{}]", (System.currentTimeMillis() - startTime)/1000);
//
//        return docList.size();
//    }
//
//    /**
//     * document 생성
//     *  - Store.YES : 인덱스를 할 값 모두를 인덱스에 저장. 검색결과 등에서 꼭 보여야 하는 내용이라면 사용한다.
//     *  - Store.NO : 값을 저장하지 않는다. Index 옵션과 혼합하여, 검색은 되데, 원본글이 필요없을 경우 사용될수 있다.
//     *  - Store.COMPRESS : 값을 압축하여 저장. 저장할 글의 내용이 크거나, 2진 바이너리 파일등에 사용한다.
//     * @param dataList 컨텐츠 리스트
//     * @return List<Document> - document 리스트
//     */
//    private List<Document> makeDocList(List<LuceneRsltVO> dataList) {
//        List<Document> docList = new ArrayList<>();
//        dataList.forEach(d -> {
//            Document document = new Document();
//            document.add(new StringField("cnttId", d.getCnttId(), Field.Store.YES));
//            document.add(new TextField("ctnt", StringUtil.isEmpty(d.getCtnt()) ? "" : d.getCtnt(), Field.Store.YES));
//
//            docList.add(document);
//        });
//
//        return docList;
//    }
//
//    /**
//     * indexWriter 생성
//     * @return IndexWriter
//     */
//    private IndexWriter createWriter() throws IOException {
//        log.info("CREATE :: INDEX DIR : [{}]", INDEX_DIR);
//// todo directory 유/무 확인
//        FSDirectory directory = FSDirectory.open(Paths.get(INDEX_DIR));
//        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
//
//        return new IndexWriter(directory, config);
//    }
//
//}
//
