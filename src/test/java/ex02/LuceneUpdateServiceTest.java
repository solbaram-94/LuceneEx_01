//package ex02;
//
//import com.lge.wee.mvc.lucene.document.LuceneReadService;
//import com.lge.wee.mvc.lucene.document.LuceneUpdateService;
//import com.lge.wee.mvc.lucene.document.vo.LuceneRsltVO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//
//import java.util.List;
//
//@Slf4j
//public class LuceneUpdateServiceTest {
//    @Test
//    public void callUpdateWrite() throws Exception {
//
//        String searchTp = "cnttId";  // cnttId(컨텐츠아이디), ctnt(컨텐츠내용)
//        String searchStr = "1509312134";
//
//        LuceneReadService luceneReadService = new LuceneReadService();
//        List<LuceneRsltVO> list = luceneReadService.searchList(searchTp, searchStr);
//        log.debug("search ctnt [{}]", list.get(0).getCtnt());
//
//        LuceneUpdateService luceneUpdateService = new LuceneUpdateService();
//        luceneUpdateService.updateCtnt(searchStr, "aaaaaaaaaaaaaa");
//
//        list = luceneReadService.searchList(searchTp, searchStr);
//        log.debug("search ctnt [{}]", list.get(0).getCtnt());
//    }
//
//}