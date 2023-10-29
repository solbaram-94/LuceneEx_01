//package com.lucene.ex02;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.StringField;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.*;
//import org.apache.lucene.search.*;
//import org.apache.lucene.store.FSDirectory;
//import org.springframework.stereotype.Service;
//
//import java.nio.file.Paths;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class LuceneUpdateService
//{
//    // junit test
//    private static final String INDEX_DIR = "/indexedFilesDir";
//
//// 서버 적용 todo 실제 서버 적용 위치 확인 필요
//// @Value("${mvc.lucene.luceneIndexFilesDir}")
//// private String INDEX_DIR;
//
//    /**
//     * 입력된 컨텐츠 아이디의 컨텐츠 변경
//     * @param cnttId - 컨텐츠 아이디
//     * @param ctnt - 컨텐츠 내용
//     * @throws Exception
//     */
//    public void updateCtnt(String cnttId, String ctnt) throws Exception {
//        Analyzer analyzer = new StandardAnalyzer();
//        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
//// Directory directory = new MMapDirectory(new File(INDEX_DIR).toPath(), NoLockFactory.INSTANCE);
//        FSDirectory directory = FSDirectory.open(Paths.get(INDEX_DIR));
//        try (IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig)) {
//            Term term = new Term("cnttId", cnttId);
//
//            try (IndexReader indexReader = DirectoryReader.open(directory)) {
//                IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//                Query query = new TermQuery(term);
//
//                TopDocs docs = indexSearcher.search(query, 1);
//// todo. 업데이트 대상(cnttId)을 찾지 못했을 경우 return
//                ScoreDoc[] hits = docs.scoreDocs;
//                Document document = indexSearcher.doc(hits[0].doc);
//                Document newDoc = this.makeDocList(document.get("cnttId"), ctnt);
//
//                indexWriter.updateDocument(term, newDoc);
//                indexWriter.commit();
//                indexWriter.close();
//
//            }
//        }
//    }
//
//    /**
//     * 문서 생성
//     * @param cnttId
//     * @param ctnt
//     * @return
//     */
//    private Document makeDocList(String cnttId, String ctnt) {
//        Document document = new Document();
//        document.add(new StringField("cnttId", cnttId, Field.Store.YES));
//        document.add(new TextField("ctnt", ctnt, Field.Store.YES));
//
//        return document;
//
//    }
//
//}
//
//
//
