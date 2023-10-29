package com.lucene.document;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;

public class LuceneWriteIndex
{
	private static final String INDEX_DIR = "c:/temp/ex1Index";

	public void LuceneWriteIndexInit() throws Exception
	{
		IndexWriter writer = createWriter();

		//Let's clean everything first
		writer.deleteAll();

		List<Document> documents = new ArrayList<>();
		
		Document document1 = createDocument(1, "Lokesh", "Gupta", "howtodoinjava.com",
				"a가 나1b다라마바사");
		documents.add(document1);

		Document document2 = createDocument(2, "Brian", "Schultz", "example.com1",
				"1a가 나a다라마바사");
		documents.add(document2);

		Document document3 = createDocument(3, "Brian", "Schultz3", "example.com2",
				"a가b나 나a다라마바사");
		documents.add(document3);

		Document document4 = createDocument(4, "Brian", "Schultz3", "example.com3",
				"1a가b나 나a다라마바사");
		documents.add(document4);

		Document document5 = createDocument(5, "Brian", "Schultz3", "example.com3",
				"1a가나 나a다라마바사");
		documents.add(document5);

		Document document6 = createDocument(6, "Brian", "Schultz3", "example.com3",
				"a가나 나1a다라마바사");
		documents.add(document6);

		Document document7 = createDocument(7, "Brian", "Schultz3", "example.com3",
				"a가나다 나a다라마바사");
		documents.add(document7);

//		Term uTerm = new Term("1", document1);

		
		writer.addDocuments(documents);
		writer.commit();
	    writer.close();
	}

	private static Document createDocument(Integer id, String firstName, String lastName, String website, String description)
	{
    	Document document = new Document();
    	document.add(new StringField("id", id.toString() , Field.Store.YES));
    	document.add(new TextField("firstName", firstName , Field.Store.YES));
    	document.add(new TextField("lastName", lastName , Field.Store.YES));
    	document.add(new TextField("website", website , Field.Store.YES));
		document.add(new TextField("description", description , Field.Store.YES));
    	return document;
    }

	/**
	 * index 생성
	 * @return IndexWriter
     */
	private static IndexWriter createWriter() throws IOException
	{
		FSDirectory dir = FSDirectory.open(Paths.get(INDEX_DIR));
		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
		IndexWriter writer = new IndexWriter(dir, config);
		return writer;
	}

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
