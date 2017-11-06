
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Rename {

	public static void readFileByLines(String className , String fileName , ArrayList<String> methodNames) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一行");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			//一次读一行，读入null时文件结束
			while ((tempString = reader.readLine()) != null) {
				//把当前行号显示出来
				System.out.println("line " + line + ": " + tempString);
				if(tempString.contains(className)){
					methodNames.add(tempString.substring(tempString.indexOf(":") + 1));
					line++;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static void writeStringIntoFile(String fileName, String content) {
		File file = new File(fileName);
 
		try (FileOutputStream fop = new FileOutputStream(file)) {
 
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			// get the content in bytes
			byte[] contentInBytes = content.getBytes();
 
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String readToString(String fileName) {
		// String encoding = "ISO-8859-1";
		File file = new File(fileName);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent);
		} catch (Exception e) {
			// System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		String location = System.getProperty("user.dir");
		String fileName = "total-cov.txt";
		
		String files[] = {"/src/test/java/org/apache/commons/dbutils/DateSerializationTest.java"};
		
		for(int i = 0 ; i < files.length ; i++){
			String InitJavaFile = null;
			if (args.length == 1) {
				InitJavaFile = args[0]; 
			}else{
				InitJavaFile = location + files[i];
			}
			String javaFileContent = readToString(InitJavaFile);
			System.out.println("Original File: ");
			System.out.println(javaFileContent);
			System.out.println("--------------------------------------------");
			ArrayList<String> methodNames = new ArrayList<String>();
			String[] classNameList = InitJavaFile.split("/");
			String className = classNameList[classNameList.length - 1];
			readFileByLines(className.substring(0 , className.indexOf(".")), fileName, methodNames);
			for(int j = 0; j < methodNames.size(); ++j){
				String currentHead = "@Order(order = "+ (j+1)+")\n" + "public void ";
				String newMethodName = currentHead + methodNames.get(j)+"()";
				javaFileContent = javaFileContent.replace("public void "+methodNames.get(j)+"()", newMethodName);
			}
			javaFileContent = javaFileContent.replace("public class", "import edu.utdallas.util.OrderedRunner;\nimport org.junit.runner.RunWith;\n@RunWith(OrderedRunner.class)\npublic class");
			writeStringIntoFile(InitJavaFile, javaFileContent);
			
			String tmp_javaFileContent = readToString(InitJavaFile);
			System.out.println("New File: ");
			System.out.println(tmp_javaFileContent);
			System.out.println("--------------------------------------------");
		}
	}
}
