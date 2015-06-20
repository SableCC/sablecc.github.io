---
---
# ObjectMacro

ObjectMacro is a powerful system for generating text. The most distinctive feature of ObjectMacro is that it keeps a clean separation between presentation, structure, and logic.

ObjectMacro provides a simple and robust language to describe the *presentation*. The language supports nested and recursive macro and text templates. ObjectMacro compiles these macros and text templates into an object model (a set of classes) in a target language of choice (such as Java). The separation of structure and logic is provided by this object model. The *structure* of the generated text is encoded in the relations between *macro objects*. The *logic* used to create this web of macro objects consists of code written in the target language of choice. By the very nature of the object model, there is a very loose link between the creation order of macro objects (logic) and object relations (structure).

## Advantages of ObjectMacro

* Clean separation between presentation, structure, and logic.
* Simple and robust language to describe text templates.
* Full syntactic and semantic error detection at text template compile time.
* A robust object model, providing type safety when creating the structure.
* Full power of a real programming language of choice, and its libraries, to encode the logic.

ObjectMacro has been specifically designed to easily target new languages. For example, the *Java* target consists of approximately 500 lines of Java code (including header comments, blank lines, etc.) and less than 300 lines of ObjectMacro templates.

## Example

As an example, we develop a program that prints a list of composers and then, for each composer, lists his birth and death dates.

We first create a file called `composer.objectmacro` with the following content:

	$macro: final_output $
	COMPOSERS
	=========
	$macro: composer(name) $
	- $name
	$end: composer $
	
	DETAILS
	=======
	$macro: composer_details(name, birth, death) $
	
	COMPOSER: $name
	BIRTH: $birth
	DEATH: $death
	$end: composer_details $
	$end: final_output $

This file contains three macros: `final_output`, `composer`, and `composer_details`. The later two are nested within the first macro.

The simplicity of the macro language allows us to visually anticipate the final output of our little program. There will be two titled sections: "COMPOSERS" listing one composer per line, and "DETAILS" listing a block of information for each composer.

Second, we use the ObjectMacro compiler to create a set of Java classes in the `templates` package:

	java -jar objectmacro.jar --target=java --package=templates composer.objectmacro

The compiler generates three files in a `templates` subdirectory, one for each macro: `MFinalOutput.java`, `MComposer.java`, and `MComposerDetails.java`.

We will only need to directly manipulate the `MFinalOutput` class. It contains:

1. a public constructor,
1. two macro creation methods:
   1. `newComposer(String pName)`
   1. `newComposerDetails(String pName, String pBirth, String pDeath)`
1. a `toString()` method that generates the final `String` representation of the macro.

Third, we write the main program in a file called `Composer.java`:

	import templates.*;
	
	public class Composer {
	  public static void main(String args[]) {
	    MFinalOutput finalOutput = new MFinalOutput();
	
	    // Bach
	    finalOutput.newComposer("Johann Sebastian Bach");
	    finalOutput.newComposerDetails("Johann Sebastian Bach", "1685", "1750");
	
	    // Handel
	    finalOutput.newComposer("George Frideric Handel");
	    finalOutput.newComposerDetails("George Frideric Handel", "1685", "1759");
	
	    // Mozart
	    finalOutput.newComposer("Wolfgang Amadeus Mozart");
	    finalOutput.newComposerDetails("Wolfgang Amadeus Mozart", "1756", "1791");
	
	    // Print
	    System.out.println(finalOutput.toString());  // That's it!
	  }
	}

The main program creates a `MFinalOutput` object, then calls the `newComposer` and `newComposerDetails` methods for each composer, and finally prints the string representation of the object.

Fourth, we compile the program:

	javac Composer.java

Finally, we run the program:

	java Composer

As we have anticipated, the program prints:

	COMPOSERS
	=========
	- Johann Sebastian Bach
	- George Frideric Handel
	- Wolfgang Amadeus Mozart
	
	DETAILS
	=======
	
	COMPOSER: Johann Sebastian Bach
	BIRTH: 1685
	DEATH: 1750
	
	COMPOSER: George Frideric Handel
	BIRTH: 1685
	DEATH: 1759
	
	COMPOSER: Wolfgang Amadeus Mozart
	BIRTH: 1756
	DEATH: 1791
	

We can see, in our little program, the separation between logic and structure:

0. The structure consists of two distinct lists: a composer list, and a composer details list.
0. The logic processes each composer once. It immediately takes two actions for each composer.

This is a very distinctive and powerful feature of ObjectMacro that sets it apart from other macro/template systems. In these other systems, one needs to iterate twice over the list of composers, as logic is guided by the presentation. ObjectMacro frees programmers from the structure of the presentation and allows them to write the code in the order that logic, not structure, dictates.

## Additional Features

The above example only illustrates the basic use of macros, named arguments, and nested macros. ObjectMacro provides additional (and very useful) features: static text blocks with parameters (to use within macros), recursive macro calls, and various helpers for lists: `separator`, `before_first`, `after_last`, `before_one`, `after_one`, `before_many`, `after_many`, and `none`.

As ObjectMacro generates a set of classes in a target language of choice and requires no other library support, programs that use ObjectMacro can be distributed without needing to distribute any additional library nor template files.

## Getting ObjectMacro

ObjectMacro is included in SableCC 4 (beta).

