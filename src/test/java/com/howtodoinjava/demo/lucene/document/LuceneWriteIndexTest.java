package com.howtodoinjava.demo.lucene.document;

import com.lucene.document.LuceneWriteIndex;
import org.junit.Test;

//import static org.junit.jupiter.api.Assertions.*;

public class LuceneWriteIndexTest {
    @Test
    public void callWrite() throws Exception {
        LuceneWriteIndex luceneWriteIndex = new LuceneWriteIndex();
        luceneWriteIndex.LuceneWriteIndexInit();
    }

}