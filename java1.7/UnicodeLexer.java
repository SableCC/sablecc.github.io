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

import org.sablecc.grammars.java_1_5.unicodepreprocessor.lexer.*;
import org.sablecc.grammars.java_1_5.unicodepreprocessor.node.*;
import org.sablecc.grammars.java_1_5.unicodepreprocessor.analysis.*;
import java.io.*;

public class UnicodeLexer extends Lexer
{
    private PushbackReader in;
    
    public UnicodeLexer(PushbackReader in)
    {
        super(in);
        this.in = in;
    }

    private Token buffer;
    
    protected void filter()
    {
        if(state.equals(State.SUB))
	    {
		if(buffer == null)
		    {
			buffer = token;
			token = null;
		    }
		else
		    {
			if(token instanceof EOF)
			    {
				buffer = null;
				state = State.NORMAL;
			    }
			else
			    {
				try
				    {
					// Unread the last token text.
					in.unread(token.getText().toCharArray());
				    }
				catch(IOException e)
				    {
					throw new RuntimeException("Error while unreading: " + e);
				    }
				
				token = buffer;
				buffer = null;
				state = State.NORMAL;
			    }
		    }
	    }
        else
	    {
		if(token instanceof TUnicodeEscape)
		    {
			String text = token.getText();
			
			// Is it SUB?
			if(text.substring(text.length() - 4).equalsIgnoreCase("001a"))
			    {
				buffer = token;
				token = null;
				state = State.SUB;
			    }
		    }
	    }
    }
}
