import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class ASTWriter {
	
    /**
     * Replaces all text in the existing file with the given text.
     */
    private static void writeFile(File f, String fileText) {
    	File dir = f.getParentFile();
    	if (dir != null){
    		dir.mkdirs();
    	}
    	
        try (FileWriter fw = new FileWriter(f, false);){
            fw.write(fileText + "\n");
        } catch (IOException e) {
			e.printStackTrace();
		}
    }

	public static void main(String[] args) throws IOException{
		String assignment = args[0];
		String fileName = args[1];
		String methodName = args[2];
		boolean addLines = false;
		if (args.length > 3 && args[3].equals("-l")){
			addLines = true;
		}
		
		File sourceDir = new File("../../assignments/java/" + assignment + "/full_src/");
		File[] submissionDirs = sourceDir.listFiles();
		for (File submissionDir: submissionDirs){
			String submissionFile = submissionDir.getPath() + "/" + fileName;
			if (new File(submissionFile).exists()){
				String commentlessCode = ASTBuilder.uncomment(ASTBuilder.readFileToString(submissionFile));
				String cleanCode = ASTBuilder.clean(commentlessCode, methodName);
				//System.out.println(commentlessCode);
				String ast = ASTBuilder.parse(commentlessCode, methodName, addLines);
				if (ast != null){
					if (addLines){
						File astFile = new File("../../assignments/java/" + assignment + "/annotated_ast/" + submissionDir.getName() + "/" + fileName + ".ast");
						writeFile(astFile, ast);
					} else {
						File astFile = new File("../../assignments/java/" + assignment + "/ast/" + submissionDir.getName() + "/" + fileName + ".ast");
						writeFile(astFile, ast);
					}
					File cleanFile = new File("../../assignments/java/" + assignment + "/src/" + submissionDir.getName());
					writeFile(cleanFile, cleanCode);
				}
			}
		}
	}
}
