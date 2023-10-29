package com.howtodoinjava.demo.lucene.document;

import org.junit.Test;
import com.lucene.document.LuceneReadIndex;

//import static org.junit.jupiter.api.Assertions.*;

public class LuceneReadIndexTest {
    @Test
    public void callReadIndex() throws Exception {
        new LuceneReadIndex("description", "a가 나1");
    }
}