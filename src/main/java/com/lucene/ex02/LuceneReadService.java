//package com.lucene.ex02;
//
//import com.lge.wee.mvc.lucene.document.vo.LuceneRsltVO;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.Term;
//import org.apache.lucene.search.*;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Pattern;
//
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class LuceneReadService
//{
//    // junit test 용
//    private static final String INDEX_DIR = "/indexedFilesDir";
//
//// 서버 적용 todo 실제 서버 적용 위치 확인 필요
//// @Value("${mvc.lucene.luceneIndexFilesDir}")
//// private String INDEX_DIR;
//
//    /**
//     * 문서 검색
//     * @param searchStr - 검색어
//     * @return - 검색 결과 리스트
//     */
//    public List<LuceneRsltVO> searchList(String searchTp, String searchStr) throws Exception {
//        if (!StringUtils.equals("cnttId", searchTp) && !StringUtils.equals("ctnt", searchTp)) {
//            return null;
//        }
//        log.debug("SEARCH - start :: searchTp = [{}], searchList = [{}]", searchTp, searchStr);
//
//        IndexSearcher searcher = createSearcher();
//        long startTime = System.currentTimeMillis();
//
//// Search by 컨텐츠
//        TopDocs foundDocs = searchContents(searcher, searchTp, searchStr);
//        log.debug("SEARCH - create :: createSearcher cnt[{}] (m)[{}]", foundDocs.totalHits, (System.currentTimeMillis() - startTime));
//
//        List<LuceneRsltVO> rsltList = new ArrayList<>();
//        LuceneRsltVO luceneRsltVO;
//        for (ScoreDoc sd : foundDocs.scoreDocs)
//        {
//            Document d = searcher.doc(sd.doc);
//            luceneRsltVO = new LuceneRsltVO();
//            luceneRsltVO.setCnttId(String.format(d.get("cnttId")));
//            String sc = "[^\uAC00-\uD7A30-9a-zA-Z]";
//            luceneRsltVO.setCtnt(String.format(d.get("ctnt").replaceAll(sc, "")));
//
//            rsltList.add(luceneRsltVO);
//            log.debug("SEARCH - result :: result : cnttId[{}], ctnt[{}]", luceneRsltVO.getCnttId(), luceneRsltVO.getCtnt());
//        }
//
//        return rsltList;
//    }
//
//    /**
//     * 컨텐츠 아이디를 이용한 문서 검색
//     * - space 로 구분된 두 단어 이상의 검색일 경우 각 단어의 and 조건 검색
//     * - StandardAnalyzer : space 로 구분해 검색
//     *   - 한글의 경우 조사 접두/접미어 등이 있을 경우 이 단어를 포함한 단어만 검색 됨
//     * - todo. 한글 검색을 위해 'KoreanAnalyzer' 검토 필요 ==> like 검색으로 대체
//     * @param searcher - searcher
//     * @param searchStr - 검색 문장
//     * @return - 검색된 document list
//     */
//    private TopDocs searchContents(IndexSearcher searcher, String searchTp, String searchStr) throws Exception {
//        Pattern pattern = Pattern.compile("\\s");
//        String[] wordArr = pattern.split(searchStr);
//
//        BooleanQuery.Builder builder = new BooleanQuery.Builder();
//        for (int i=0; i<wordArr.length; i++) {
//            TermQuery query = new TermQuery(new Term(searchTp, wordArr[i]));
//            builder.add(query, BooleanClause.Occur.MUST);
//        }
//
//        BooleanQuery booleanQuery = builder.build();
//// 검색 문서 최대 개수
//        int maxDcoCount = 1000;
//        return searcher.search(booleanQuery, maxDcoCount);
//    }
//
//// /**
//// * 컨텐츠 아이디를 이용한 문서 검색
//// * - StandardAnalyzer : space 로 구분해 검색
//// *   - 한글의 경우 조사 접두/접미어 등이 있을 경우 이 단어를 포함한 단어만 검색 됨
//// * - todo. 한글 검색을 위해 'KoreanAnalyzer' 검토 필요
//// * @param searcher - searcher
//// * @param searchStr - 검색어
//// * @return - 검색된 document list
//// */
//// private TopDocs searchByWord(IndexSearcher searcher, String searchStr) throws Exception {
//// String searchTpCtnt = "ctnt";
//// QueryParser queryParser = new QueryParser(searchTpCtnt, new StandardAnalyzer());
////
//// Query idQuery = queryParser.parse(searchStr);
//// // 검색 문서 최대 개수
//// int maxDcoCount = 1000;
//// TopDocs topDocs = searcher.search(idQuery, maxDcoCount);
////
//// return topDocs;
//// }
////
//// /**
//// * 컨텐츠 문장을 이용한 문서 검색
//// * - space 로 구분된 두 단어 이상의 검색일 경우 각 단어의 and 조건 검색
//// * - StandardAnalyzer : space 로 구분해 검색
//// *   - 한글의 경우 조사 접두/접미어 등이 있을 경우 이 단어를 포함한 단어만 검색 됨
//// * - todo. 한글 검색을 위해 'KoreanAnalyzer' 검토 필요
//// * @param searcher - searcher
//// * @param searchStr - 검색 문장
//// * @return - 검색된 document list
//// */
//// private TopDocs searchBySentence(IndexSearcher searcher, String searchStr) throws Exception {
//// Pattern pattern = Pattern.compile("\\s");
//// String[] wordArr = pattern.split(searchStr);
////
//// BooleanQuery.Builder builder = new BooleanQuery.Builder();
//// for (int i=0; i<wordArr.length; i++) {
//// TermQuery query = new TermQuery(new Term("ctnt", wordArr[i]));
//// builder.add(query, BooleanClause.Occur.MUST);
//// }
////
//// BooleanQuery booleanQuery = builder.build();
//// // 검색 문서 최대 개수
//// int maxDcoCount = 1000;
//// return searcher.search(booleanQuery, maxDcoCount);
//// }
//
//    /**
//     * IndexSearcher 생성
//     * @return IndexSearcher
//     */
//    private IndexSearcher createSearcher() throws IOException {
//        Directory dir = FSDirectory.open(Paths.get(INDEX_DIR));
//        IndexReader reader = DirectoryReader.open(dir);
//// dir.close();
//
//        return new IndexSearcher(reader);
//    }
//}
//
//
//
//
