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
		
		File sourceDir = new File("61b_data/src/" + assignment + "/");
		File[] submissionDirs = sourceDir.listFiles();
		for (File submissionDir: submissionDirs){
			String submissionFile = submissionDir.getPath() + "/" + fileName;
			if (new File(submissionFile).exists()){
				String ast = ASTBuilder.parse(ASTBuilder.readFileToString(submissionFile), methodName);
				if (ast != null){
					File astFile = new File("61b_data/ast/" + assignment + "/" + submissionDir.getName() + "/" + fileName + ".ast");
					writeFile(astFile, ast);
				}
			}
		}
	}
}
