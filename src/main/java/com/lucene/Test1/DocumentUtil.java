package com.lucene.Test1;


import java.util.Arrays;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

public class DocumentUtil {

    public static Document getDocument(String id, String title, String description, String blog) {
        Document doc = new Document();
        doc.add(new TextField("id", id, Field.Store.YES));
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new TextField("description", description, Field.Store.NO));
        doc.add(new TextField("blog", blog, Field.Store.YES));

        return doc;

    }

    public static List<Document> getDocuments() {
        Document doc1 = getDocument("1", "JavaWorld",
                "The original independent resource for Java developers, architects, and managers.", " javaworld.com");
        Document doc2 = getDocument("2", "Oracle Blogs | The Java Source",
                " Java powers more than 4.5 billion devices including 800 million computers and 1.5 billion cell phones. If you love Java, this is the blog you must follow.",
                "blogs.oracle.com/java");
        Document doc3 = getDocument("3", "A Java geek",
                "Nicolas Fränkel's blog. IT architect focusing on Java, Java EE, and their surrounding ecosystems. He is a trainer, book writer, speaker & blogger.",
                "blog.frankel.ch");
        Document doc4 = getDocument("4", "Self Learning Java", "Learn Java fundamentals and other java libraries",
                "self-learning-java-tutorial.blogspot.com");

        return Arrays.asList(doc1, doc2, doc3, doc4);

    }
}