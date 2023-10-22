//package com.howtodoinjava.demo.lucene.document;
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.NumericDocValuesField;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig.OpenMode;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.index.Term;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.apache.lucene.util.Version;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.nio.file.Paths;
//import java.util.Date;
//
///** Index all text files under a directory.
// * <p>
// * This is a command-line application demonstrating simple Lucene indexing.
// * Run it with no command-line arguments for usage information.
// */
//public class IndexFiles {
//	private static final String INDEX_DIR = "c:/temp/ex1Index";
//
//
//	private IndexFiles() {}
//
//	/** Index all text files under a directory. */
//	public static void main(String[] args) {
//
//		String docsPath = "d:/"; //1. 색인 대상 문서가 있는 경로
//		String indexPath = "d:/lucene_index/"; //2. 색인 파일이 만들어질 경로
//
//		final File file = new File(docsPath);
//		FSDirectory docDir = null;
////		try {
////			docDir = FSDirectory.open(Paths.get(INDEX_DIR));
////		} catch (IOException e) {
////			throw new RuntimeException(e);
////		}
////		if (!docDir.exists() || !docDir.canRead()) {
////			System.out.println("Document directory '" +docDir.getDirectory()+ "' does not exist or is not readable, please check the path");
////			System.exit(1);
////		}
//
//		Date start = new Date();
//
//		try {
//			System.out.println("Indexing to directory '" + indexPath + "'...");
//
//			//3. 여기는 루씬에서 색인을 위한 IndexWriter를 생성하는 부분입니다.
//			Directory dir = FSDirectory.open(Paths.get(INDEX_DIR));
//			Analyzer analyzer = new StandardAnalyzer(); //문서 내용을 분석 할 때 사용 될 Analyzer
//			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
//
//			boolean create = true; //4. 색인 파일을 새로 생성 할 것인지의 여부
//
////			if (create) {
////				// Create a new index in the directory, removing any
////				// previously indexed documents:
////				iwc.setOpenMode(OpenMode.CREATE); //5. 새로운 인덱스 파일을 만든다. 기존 인덱스 파일은 삭제됩니다.
////			} else {
//				// Add new documents to an existing index:
//				iwc.setOpenMode(OpenMode.CREATE_OR_APPEND); //6. 원래 있던 인덱스 파일에 문서를 추가합니다.
////			}
//
//			// Optional: for better indexing performance, if you
//			// are indexing many documents, increase the RAM
//			// buffer.  But if you do this, increase the max heap
//			// size to the JVM (eg add -Xmx512m or -Xmx1g):
//			//
//			// iwc.setRAMBufferSizeMB(256.0); //7. IndexWriterConfig가 새로 생긴 클래스입니다. 이 부분은 색인 속도 증가를 위해
//			//     사용되는 옵션으로 보입니다.
//
//			IndexWriter writer = new IndexWriter(dir, iwc); //8. 드디어 IndexWriter를 생성합니다.
//			indexDocs(writer, file); //9. 색인 대상 문서들이 있는 디렉토리에서 문서를 읽어 색인을 합니다.
//
//			// NOTE: if you want to maximize search performance,
//			// you can optionally call optimize here.  This can be
//			// a costly operation, so generally it's only worth
//			// it when your index is relatively static (ie you're
//			// done adding documents to it):
//			//
//			// writer.optimize();
//
//			writer.close();
//
//			Date end = new Date();
//			System.out.println(end.getTime() - start.getTime() + " total milliseconds");
//
//		} catch (IOException e) {
//			System.out.println(" caught a " + e.getClass() +
//					"\n with message: " + e.getMessage());
//		}
//	}
//
//	/**
//	 * Indexes the given file using the given writer, or if a directory is given,
//	 * recurses over files and directories found under the given directory.
//	 *
//	 * NOTE: This method indexes one document per input file.  This is slow.  For good
//	 * throughput, put multiple documents into your input file(s).  An example of this is
//	 * in the benchmark module, which can create "line doc" files, one document per line,
//	 * using the
//	 * <a href="../../../../../contrib-benchmark/org/apache/lucene/benchmark/byTask/tasks/WriteLineDocTask.html"
//	 * >WriteLineDocTask</a>.
//	 *
//	 * @param writer Writer to the index where the given file/dir info will be stored
//	 * @param file The file to index, or the directory to recurse into to find files to index
//	 * @throws IOException
//	 */
//	static void indexDocs(IndexWriter writer, File file)
//			throws IOException {
//		// do not try to index files that cannot be read
//		if (file.canRead()) {
//			if (file.isDirectory()) {
//				String[] files = file.list();
//				// an IO error could occur
//				if (files != null) {
//					for (int i = 0; i < files.length; i++) {
//						if(files[i].endsWith("txt"))
//							indexDocs(writer, new File(file, files[i])); //10. 재귀호출을 통해 파일이 디렉토리가 아닌 경우 문서를 색인 합니다.
//					}
//				}
//			} else {
//
//				FileInputStream fis;
//				try {
//					fis = new FileInputStream(file); //11. 문서내용을 가져오기 위해 Stream을 엽니다.
//				} catch (FileNotFoundException fnfe) {
//					// at least on windows, some temporary files raise this exception with an "access denied" message
//					// checking if the file can be read doesn't help
//					return;
//				}
//
//				try {
//
//					// make a new, empty document
//					//12. 문서 색인의 단위입니다. 하나의 Document가 하나의 Row입니다.
//					Document doc = new Document();
//
//					// Add the path of the file as a field named "path".  Use a
//					// field that is indexed (i.e. searchable), but don't tokenize
//					// the field into separate words and don't index term frequency
//					// or positional information:
//
//					//13. 문서 색인단위가 Document인데, 이 Document에는 여러개의 필드가 포함될 수 있습니다.
//					//    이 필드를 생성합니다. 아래의 경우 path라는 필드명으로 파일의 path를 색인합니다.
//					//     기타 옵션등에 대해서는 나중에 다시 설명을 할 예정입니다.
////					Field pathField = new Field("path", file.getPath(), Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS);
////					pathField.setOmitTermFreqAndPositions(true);
//					Field pathField = new Field("path", file.getPath(), Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS);
//					pathField.setsetOmitTermFreqAndPositions(true);
//					doc.add(pathField);
//
//					// Add the last modified date of the file a field named "modified".
//					// Use a NumericField that is indexed (i.e. efficiently filterable with
//					// NumericRangeFilter).  This indexes to milli-second resolution, which
//					// is often too fine.  You could instead create a number based on
//					// year/month/day/hour/minutes/seconds, down the resolution you require.
//					// For example the long value 2011021714 would mean
//					// February 17, 2011, 2-3 PM.
//
//					//14. Field에는 숫자를 색인 할 수 있는 전용 클래스도 존재합니다.
//					//    마찬가지로 나중에 설명드리겠지만, 정렬등에서 속도에 이득이 있습니다.
//					NumericField modifiedField = new NumericField("modified");
//					modifiedField.setLongValue(file.lastModified());
//					doc.add(modifiedField);
//
//					// Add the contents of the file to a field named "contents".  Specify a Reader,
//					// so that the text of the file is tokenized and indexed, but not stored.
//					// Note that FileReader expects the file to be in UTF-8 encoding.
//					// If that's not the case searching for special characters will fail.
//
//					//15. path, modified, 그리고 아래의 contents라는 이름의 필드를 Document에 추가합니다.
//					//    마찬가지로 나중에 다시 말씀드리겠지만, 이 예제에서는 필드에 String, Numeric, Reader등 여러 타입의
//					//     내용을 추가 할 수 있다는 것을 보여줍니다.
//					doc.add(new Field("contents", new BufferedReader(new InputStreamReader(fis, "UTF-8"))));
//
//					if (writer.getConfig().getOpenMode() == OpenMode.CREATE) { //16. 인덱스 파일을 새로 생성하도록 되어 있는 옵션이면 add...
//						// New index, so we just add the document (no old document can be there):
//						System.out.println("adding " + file);
//						writer.addDocument(doc);
//					} else {
//						// Existing index (an old copy of this document may have been indexed) so
//						// we use updateDocument instead to replace the old one matching the exact
//						// path, if present:
//						System.out.println("updating " + file); //17. Create or Update라면 update를 합니다.
//						// 이문호님께서 알려주셨습니다.
//						// updateDocument는 2.x 부터 생긴 메서드입니다. 그 이전버전에서는 update는 delete -> insert 메서드를 사용해서
//						//  구현해야 합니다.
//						writer.updateDocument(new Term("path", file.getPath()), doc);
//					}
//
//				} finally {
//					fis.close();
//				}
//			}
//		}
//	}
//}
