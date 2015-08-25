import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public class FileProcess {
	
	/**
	 * Read the Object File from the given
	 * abstract File path and return the object
	 * @param <T>
	 * @param f
	 * @return 
	 * @return the object which is saved in this file
	 */
	public static  <T>  T read(File f){
		ObjectInputStream i;
		try {
			i = new ObjectInputStream(new FileInputStream(f.toString()));;
			T c=(T) i.readObject();
			return c;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Write the obj to the given abstract file
	 * @param <T>
	 * @param obj
	 * @param f
	 */
	public static <T> void save(T obj,File f){
		ObjectOutputStream o;
		try {
			o = new ObjectOutputStream(new FileOutputStream(f.toString()));
			o.writeObject(obj);
			o.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Thie helper function is to copy one file 
	 * from sourceFile to destFile
	 * If two files have the same name , the old
	 * one will be replaced
	 * @param sourceFile  
	 * 				File to be copied
	 * @param destFile
	 * 				File being copied
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static void copyFile(File sourceFile, File destFile) throws IOException {
	    if(!destFile.exists()) {
	        destFile.createNewFile();
	    }

	    FileChannel source = null;
	    FileChannel destination = null;

	    try {
	        source = new FileInputStream(sourceFile).getChannel();
	        destination = new FileOutputStream(destFile).getChannel();
	        destination.transferFrom(source, 0, source.size());
	    }
	    finally {
	        if(source != null) {
	            source.close();
	        }
	        if(destination != null) {
	            destination.close();
	        }
	    }
	}
	
	public static void copyFiles(ArrayList<File> sourceFiles, File dest ) throws IOException{	
		for(File f:sourceFiles){
			FileProcess.copyFile(f, new File(dest.toString()+"/"+f.getName()));
		}
	}
	
	/**
	 * Remove files from the dest
	 * the named is provided by
	 * the sourceFiles
	 * @param sourceFiles
	 * @param dest
	 */
	public static void removeFiles(ArrayList<File> sourceFiles, File dest){
		for(File f:sourceFiles){
			File m=new File(dest.toString()+"/"+f.getName());
			m.delete();
		}
	}
	
	public static void removeFiles(ArrayList<File> sourceFiles){
		for(File f:sourceFiles){
			File m=new File(f.toString());
			m.delete();
		}
	}
	
	public static void moveFile(File sourceFile, File destFile) throws IOException{
		copyFile(sourceFile,destFile);
		sourceFile.delete();
	}
	
	public static void main(String[] args){
		try {
			FileProcess.copyFile(new File("coffee.txt"), new File(".gitlet/coffee.txt"));
			ArrayList<File> a=new ArrayList<File>();
			a.add(new File(".gitlet/commit#1/a.txt"));
			a.add(new File(".gitlet/commit#1/b.txt"));
			FileProcess.removeFiles(a);
			FileProcess.save(new Point(3,4), new File("string.obj"));
			Point s=FileProcess.read(new File("string.obj"));
			System.out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Pass the name of the directory
	 * return an ArrayList which contains the name of the
	 * file in that directory.
	 * @param dir
	 * @return 
	 */
	public static ArrayList<File> toArrayList(File dir){
		File[] files=dir.listFiles();
		System.out.println(dir.toString());
		ArrayList<File> a=new ArrayList<File>();
		for(int i=0;i<files.length;i++){
			a.add(new File(files[i].getName()));
		}
		return a;
	}
	
	/**
	 * Pass the name of the directory
	 * return an ArrayList which contains the absolute of the
	 * file in that directory.
	 * @param dir
	 * @return 
	 */
	public static ArrayList<File> fileDirInArrayList(File dir){
		File[] files=dir.listFiles();
		ArrayList<File> a=new ArrayList<File>();
		for(int i=0;i<files.length;i++){
			a.add(files[i]);
		}
		return a;
	}
	
	/**
	 * check if the given file has been modified in the 
	 * two directories
	 * @param fileName
	 * 			only name of the file.exclude directory
	 * @param src
	 * 			src directory
	 * @param target
	 * 			target directory
	 * @return
	 */
	public static boolean isFileModified(String fileName, String src, String target){
		try {
			String srcS=FileUtils.readFileToString(new File(src+"/"+fileName));
			String targetS=FileUtils.readFileToString(new File(target+"/"+fileName));
			if(!srcS.equals(targetS)){
				return true;
			}else{
				return false;
			}
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * check if two files are equal or not 
	 * @param file1
	 * @param file2
	 * @return
	 */
	public static boolean equals(File file1, File file2){
		BufferedReader i;
		BufferedReader j;
		try{
			i = new BufferedReader( new FileReader(file1.toString()));
			j = new BufferedReader( new FileReader(file2.toString()));
			while (i.ready() || j.ready()){
				if (i.read() != j.read()){
					return false;
				}
			}
			return true;
		} catch (IOException e) {
			return false;
		}
		
	}

}
