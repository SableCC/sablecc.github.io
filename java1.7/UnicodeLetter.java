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

public class UnicodeLetter 
{
    public static void main(String[] arguments) 
    {
        int begin = 0;
        int end = 0;
        boolean letter = false;
	
        for(int c = 0; c <= 65535; c++)
	    {
		if(letter)
		    {
			if(!isLetter(c))
			    {
				letter = false;
				print(begin, end);
			    }
			else
			    {
				end = c;
			    }
		    }
		else
		    {
			if(isLetter(c))
			    {
				letter = true;
				begin = end = c;
			    }
		    }
	    }
	
        if(letter)
	    {
		print(begin, end);
	    }
	
        System.out.println();
    }
    
    private static boolean isLetter(int i)
    {
        char c = (char) i;
	
        return Character.isLetter(c);
    }
    
    private static byte counter = 0;
    
    private static void print(int begin, int end)
    {
        if(counter == 0)
	    {
		System.out.print("\t\t");
	    }
	
        System.out.print("[0x" + hex(begin) + "..0x" + hex(end) + "] |");
	
        if(counter++ == 3)
	    {
		System.out.println();
		counter = 0;
	    }
        else
	    {
		System.out.print(" ");
	    }
    }
    
    private static String hex(int i)
    {
        String s = Integer.toHexString(i);
        for(int len = s.length(); len < 4; len++)
	    {
		s = "0" + s;
	    }
	
        return s;
    }
}
