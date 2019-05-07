package com.active.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*��������java����*/
public class CompileJava extends Compile{

	@Override
	public void storeAnswerCode(String title,String s) {
		// TODO Auto-generated method stub
		try {
			File file = new File(CurrentPath.getCurrentPath() + "/res/Problem/" + title + "/java/Test.java");
			if(!file.exists()) {/*�����ڸ��ļ����򴴽����ļ�*/
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			fw.write(getPackage() + s);
			fw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	/*�õ�����������İ�*/
	private String getPackage() {
		return "import java.lang.*;\r\n" + 
				"import java.io.*;\r\n" + 
				"import java.util.*;\n";
	}
	@Override
	public String compile(String title) {
		// TODO Auto-generated method stub
		String res = "";
		res += ExecUtil.execProcessBuider("javac","Test.java",
				CurrentPath.getCurrentPath()+ "/res/Problem/" + title + "/java");
		res += ExecUtil.execProcessBuider("javac","Data.java",
				CurrentPath.getCurrentPath()+ "/res/Problem/" + title + "/java");
		if(res.length() != 0) {
			return "Compile error";
		}
		res += ExecUtil.execProcessBuider("java","Data",
				CurrentPath.getCurrentPath() + "/res/Problem/" + title + "/java");
		return res;
	}
	
}
