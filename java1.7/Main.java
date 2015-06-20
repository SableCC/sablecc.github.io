/* This file is part of the Java 1.5 grammar for SableCC.
 *
 * Copyright 2006 Etienne M. Gagnon <egagnon@j-meg.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.sablecc.grammars.java_1_5.lexer.*;
import org.sablecc.grammars.java_1_5.parser.*;
import org.sablecc.grammars.java_1_5.node.*;
import org.sablecc.grammars.java_1_5.analysis.*;
import java.io.*;

public class Main
{
    public static void main(String[] arguments) throws Exception
    {
	BufferedReader stdin = new BufferedReader
	    (new InputStreamReader(System.in));

	String line;
	while((line = stdin.readLine()) != null)
	    {
		FileReader in = new FileReader(line);

		try
		    {
		UnicodePreprocessor preprocessor = new UnicodePreprocessor
		    (new PushbackReader
		     (new BufferedReader
		      (in), 1024));
		
		System.out.print(line + ": ");
		Lexer lexer = new Lexer(new PushbackReader(preprocessor, 1024));
		Parser parser = new Parser(lexer);
		try
		    {
			Start ast = parser.parse();
			System.out.println("OK.");
		    }
		catch(Exception e)
		    {
			System.out.println("****** Error *****.");
			throw e;
		    }
		    }
		finally
		    {
			in.close();
		    }
	    }
    }
}
