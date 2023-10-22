package com.howtodoinjava.demo.lucene.document;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.store.NoLockFactory;

import java.io.File;

public class LuceneUpdateWriteIndex
{
	private static final String INDEX_DIR = "c:/temp/ex1Index";

	public void LuceneUpdateWriteIndexInit() throws Exception
	{
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig indexWriterConfig1 = new IndexWriterConfig(analyzer);

		Directory directory = new MMapDirectory(new File(INDEX_DIR).toPath(), NoLockFactory.INSTANCE);

		try (IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig1)) {

			Term term = new Term("id", "1");

			try (IndexReader indexReader = DirectoryReader.open(directory)) {
				IndexSearcher indexSearcher = new IndexSearcher(indexReader);
				Query query = new TermQuery(term);

				TopDocs docs = indexSearcher.search(query, 1);
				ScoreDoc[] hits = docs.scoreDocs;

				Document document = indexSearcher.doc(hits[0].doc);

				Document newDoc = DocumentUtilT1.getDocument(document.get("id"), "Java Revisited",
						"Java Design Patterns and Java interview Questions", "https://javarevisited.blogspot.com/");

				indexWriter.updateDocument(term, newDoc);
			}
		}

	}

//	private static Document createDocument(Integer id, String firstName, String lastName, String website)
//	{
//    	Document document = new Document();
//    	document.add(new StringField("id", id.toString() , Field.Store.YES));
//    	document.add(new TextField("firstName", firstName , Field.Store.YES));
//    	document.add(new TextField("lastName", lastName , Field.Store.YES));
//    	document.add(new TextField("website", website , Field.Store.YES));
//    	return document;
//    }
//
//	/**
//	 * index 생성
//	 * @return IndexWriter
//     */
//	private static IndexWriter createWriter() throws IOException
//	{
//		FSDirectory dir = FSDirectory.open(Paths.get(INDEX_DIR));
//		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
//		IndexWriter writer = new IndexWriter(dir, config);
//		return writer;
//	}

	/**
	 * index 추가/생성
	 * @return IndexWriter
     */
//	private static IndexWriter updateWriter() throws IOException
//	{
//		FSDirectory dir = FSDirectory.open(Paths.get(INDEX_DIR));
//		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
//		IndexWriter writer = new IndexWriter(dir, config);
//		writer.updateDocument(new Term("path", file.toString()), doc);
//		return writer;
//	}

//
//    // 최초 구동 시 index 생성 : 전체 삭제 후 생성
//	// 이후 index 수정/추가 : 삭제 없이 수정/추가
//	private static Index
//	import org.apache.lucene.index.IndexModifier;
//	Analyzer analyzer = new StandardAnalyzer(); // 인덱스를 /tm/index에 생성하고, 만약 존재하면 덮어쓰기:
//	IndexModifier indexModifier = new IndexModifier("/tmp/index", analyzer, true);
//	Document doc = new Document(); doc.add(new Field("id", "1", Field.Store.YES, Field.Index.UN_TOKENIZED));
//	doc.add(new Field("body", "단순 테스트", Field.Store.YES, Field.Index.TOKENIZED));
//	indexModifier.addDocument(doc);
//	int deleted = indexModifier.delete(new Term("id", "1"));
//	System.out.println("삭제된 " + deleted + " 문서");
//	indexModifier.flush();
//	System.out.println(indexModifier.docCount() + " 문서가 인덱스 안에 있습니다.");
//	indexModifier.close();
}
