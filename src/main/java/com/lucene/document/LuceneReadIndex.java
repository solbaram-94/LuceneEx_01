package com.lucene.document;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class LuceneReadIndex
{
	private static final String INDEX_DIR = "c:/temp/ex1Index";

	public LuceneReadIndex(String searchTp, String searchStr) throws Exception
	{
		IndexSearcher searcher = createSearcher();
		
//		TopDocs foundDocs = searchByDescription(searcher, searchTp, searchStr);
		TopDocs foundDocs = searchWildCard(searcher, searchTp, searchStr);
		System.out.println("Total Results :: " + foundDocs.totalHits);
		System.out.println("========================================");
		for (ScoreDoc sd : foundDocs.scoreDocs)
		{
			Document d = searcher.doc(sd.doc);
			System.out.printf((d.get("id")) + ", " + (d.get("description")) + "%n");
		}
		System.out.println("========================================");
	}

	private static TopDocs searchWildCard(IndexSearcher searcher, String searchTp, String searchStr) throws Exception {
		Pattern pattern = Pattern.compile("\\s");
		String[] wordArr = pattern.split(searchStr);

		BooleanQuery.Builder builder = new BooleanQuery.Builder();
		for (String s : wordArr) {

//			TermQuery query = new TermQuery(new Term(searchTp, s));
			// wildcard query
//			Query query = new WildcardQuery(new Term(searchTp, "*"+s+"*"));
			// prefix query
			PrefixQuery query = new PrefixQuery(new Term(searchTp, s));
			builder.add(query, BooleanClause.Occur.MUST);
		}

		// boolean query
		BooleanQuery booleanQuery = builder.build();
		return searcher.search(booleanQuery, 100);

	}

	private static TopDocs searchByDescription(IndexSearcher searcher, String searchTp, String searchStr) throws Exception
	{
		Pattern pattern = Pattern.compile("\\s");
        String[] wordArr = pattern.split(searchStr);
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        for (String s : wordArr) {
            TermQuery query = new TermQuery(new Term(searchTp, s));
            builder.add(query, BooleanClause.Occur.MUST);
        }
		BooleanQuery booleanQuery = builder.build();
		return searcher.search(booleanQuery, 100);
	}

	private static TopDocs searchByFirstName(String firstName, IndexSearcher searcher) throws Exception
	{
		QueryParser qp = new QueryParser("firstName", new StandardAnalyzer());
		Query firstNameQuery = qp.parse(firstName);
		TopDocs hits = searcher.search(firstNameQuery, 10);
		return hits;
	}

	private static TopDocs searchById(Integer id, IndexSearcher searcher) throws Exception
	{
		QueryParser qp = new QueryParser("id", new StandardAnalyzer());
		Query idQuery = qp.parse(id.toString());
		TopDocs hits = searcher.search(idQuery, 10);
		return hits;
	}

	private static IndexSearcher createSearcher() throws IOException {
		Directory dir = FSDirectory.open(Paths.get(INDEX_DIR));
		IndexReader reader = DirectoryReader.open(dir);
        return new IndexSearcher(reader);
	}
}
