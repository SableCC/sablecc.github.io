---
---
# Java 1.5 Grammar

Here is a Java 1.5 grammar for SableCC.

## Remarks

* The grammar is LALR(1).
* The complete parser requires a Unicode preprocessor in addition to the normal lexer/parser. The code of a complete parser program is provided.
* The parser has not been tested with Java files containing unusual Unicode characters. It should work, but we would appreciate any feedback you have.
* The grammar is based on the natural grammar exposed throughout the text of the third edition of the Java Language Specification. It is **not** based on the LL grammar exposed in Chapter 18.

## Java 1.5 Grammar Files

* [java-1.5.sablecc](java-1.5.sablecc): This file contains the Java 1.5 grammar.
* [java-1.5-preprocessor.sablecc](java-1.5-preprocessor.sablecc): This file contains a grammar for the lexical Unicode preprocessor.
* [Main.java](Main.java): This file contains the source code the the main class of a test application that launches the Java 1.5 parser.
* [UnicodePreprocessor.java](UnicodePreprocessor.java): This files contains the source code of the Unicode preprocessor.
* [UnicodeLexer.java](UnicodeLexer.java): This file contains a customized lexer for the Unicode preprocessor.

## Additional Files

* [UnicodeLetter.java](UnicodeLetter.java): This file contains a class that could be used to improve the lexical part of the Java 1.5 grammar.
* [java-1.5-non-lalr.sablecc](java-1.5-non-lalr.sablecc): This file contains the grammar as it was before the LALR(1) changes.

