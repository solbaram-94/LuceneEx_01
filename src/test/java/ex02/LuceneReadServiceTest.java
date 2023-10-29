//package ex02;
//
//import com.lge.wee.mvc.lucene.document.LuceneReadService;
//import com.lge.wee.mvc.lucene.document.vo.LuceneRsltVO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//
//import java.util.List;
//
//
//@Slf4j
//public class LuceneReadServiceTest {
//
//    @Test
//    public void callSearchBySentence() throws Exception {
//        String searchTp = "ctnt";
//        String searchStr = "테스트";
//        LuceneReadService luceneCreateService = new LuceneReadService();
//        // 검색어 조회
//        List<LuceneRsltVO> list = luceneCreateService.searchList(searchTp, searchStr);
//
//        log.debug("rslt total [{}]", list.size());
////        list.forEach(d -> {
////            log.debug("rslt : {}. {}. {}. {}", d.getCnttId(), d.getCnttTpCd(), d.getTitle(), d.getUrl());
////        });
//    }
//
//    @Test
//    public void callReadService() throws Exception {
//        String searchTp = "cnttId";
//        String searchStr = "1509312134";
//        LuceneReadService luceneCreateService = new LuceneReadService();
//        // 검색어 조회
//        List<LuceneRsltVO> list = luceneCreateService.searchList(searchTp, searchStr);
//
//        log.debug("rslt total [{}]", list.size());
////        list.forEach(d -> {
////            log.debug("rslt : {}. {}. {}. {}", d.getCnttId(), d.getCnttTpCd(), d.getTitle(), d.getUrl());
////        });
//    }
//}
//
//
