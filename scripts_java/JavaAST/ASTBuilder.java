import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
 
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
 
public class ASTBuilder {
 
	//use ASTParse to parse string
	public static String parse(String fileContents, String methodName) {
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setSource(fileContents.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		SimpleVisitor sv = new SimpleVisitor(methodName);
		cu.accept(sv);
		return sv.toString();
	}
 
	//read file content into a string
	public static String readFileToString(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
 
		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
 
		reader.close();
 
		return  fileData.toString();	
	}
 
	public static void main(String... args) throws IOException {
		String fileName = args[0];
		String methodName = args[1];
		System.out.println(parse(readFileToString(fileName), methodName));
	}
}