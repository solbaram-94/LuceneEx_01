package com.howtodoinjava.demo.lucene.Test1;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.MultiBits;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.store.NoLockFactory;
import org.apache.lucene.util.Bits;

//import com.howtodoinjava.demo.lucene.Test1.DocumentUtil;

public class App {

    private static void printAllDocuments(Directory directory) throws IOException {

        try (IndexReader indexReader = DirectoryReader.open(directory)) {
            System.out.println("All Documents in Lucene Index");
            Bits liveDocs = MultiBits.getLiveDocs(indexReader);
            for (int i = 0; i < indexReader.maxDoc(); i++) {
                if (liveDocs != null && !liveDocs.get(i))
                    continue;

                Document doc = indexReader.document(i);
                System.out.println(doc.get("id") + ", " + doc.get("title") + ", " + doc.get("blog"));
            }

            System.out.println();
        }

    }

    public static void main(String args[]) throws IOException {

        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig1 = new IndexWriterConfig(analyzer);

        Directory directory = new MMapDirectory(new File("c:/temp/lucene/T1").toPath(), NoLockFactory.INSTANCE);

        try (IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig1)) {

            List<Document> documents = DocumentUtil.getDocuments();

            System.out.println("Adding " + documents.size() + " documents to Lucene");
            indexWriter.addDocuments(documents);
            indexWriter.commit();

            printAllDocuments(directory);

            Term term = new Term("id", "2");

            try (IndexReader indexReader = DirectoryReader.open(directory)) {
                IndexSearcher indexSearcher = new IndexSearcher(indexReader);
                Query query = new TermQuery(term);

                TopDocs docs = indexSearcher.search(query, 1);
                ScoreDoc[] hits = docs.scoreDocs;

                Document document = indexSearcher.doc(hits[0].doc);

                Document newDoc = DocumentUtil.getDocument(document.get("id"), "Java Revisited",
                        "Java Design Patterns and Java interview Questions", "https://javarevisited.blogspot.com/");

                indexWriter.updateDocument(term, newDoc);
            }

        }
        printAllDocuments(directory);

    }
}