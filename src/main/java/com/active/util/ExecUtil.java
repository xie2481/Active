package com.active.util;
/*ִ��cmd�������*/
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExecUtil {
	public static String exec(String command) {
        String executeCommand ="cmd /c "+command;
        String rs = "";
        Process cmdProcess = null;
        try {
        	
            cmdProcess = Runtime.getRuntime().exec(executeCommand);
            int waitFor = cmdProcess.waitFor();
            //�ѱ�׼��������ʹ��������������
            rs += CmdExecuteResultInfo(cmdProcess.getInputStream());
            String tmp = CmdExecuteResultInfo(cmdProcess.getErrorStream());
            if(tmp.length() != 0)
            	rs = tmp;
            cmdProcess.getOutputStream().close();
            
        } catch (IOException e) {
        	
            e.printStackTrace();
            
        } catch (InterruptedException e) {

        	e.printStackTrace();
		}finally{
        	
        	clearInBackground(cmdProcess.getInputStream());
        	clearInBackground(cmdProcess.getErrorStream());
        }
        return rs;
    }
    
    /**
     *����ProcessBuilder��������̣����Զ����  builder.redirectErrorStream(true);�� 
     @return
     @param
     */
    public static String execProcessBuider(String... command) {
    	
        String rs = "";
        //ָ����ȡ���������ݱ���,�����
        String charset = "UTF-8";
        try {
        	
            ProcessBuilder builder = new ProcessBuilder(command[0],command[1]);
            builder.directory(new File(command[2]));
            builder.redirectErrorStream(true);
            Process cmdProcess = builder.start();
            rs = CmdExecuteResultInfo(cmdProcess.getInputStream());
            cmdProcess.getOutputStream().close();
            
        } catch (IOException e) {
        	
            e.printStackTrace();
        }
        return rs;
    }
    
    /**
     * 
     * cmd���ص���Ϣ
     * @param 
     * @return String
     * @throws
     */
    private static String CmdExecuteResultInfo(InputStream inputStream) {

    	
        StringBuilder builder = new StringBuilder();
        BufferedReader cmdExecuteInfoReader = null;
        try {

            cmdExecuteInfoReader = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
            String cmdExecuteInfoLine;
            while ((cmdExecuteInfoLine = cmdExecuteInfoReader.readLine()) != null) {
                
            	builder.append(cmdExecuteInfoLine);
            	builder.append("\n");
            }
            
            return builder.toString();

        } catch (Exception e) {
        	
            e.printStackTrace();
            
        }finally{
			try {
				if (cmdExecuteInfoReader != null) {

					cmdExecuteInfoReader.close();

				}
			} catch (IOException e) {

				e.printStackTrace();
			}
        }
        return null;
    }
   
    static void clearInBackground(final InputStream is) {  
        new Thread(new Runnable(){  
            public void run(){  
                try{  
                    
                	is.close();
                	
                } catch(IOException e){   
                	e.printStackTrace();
                }  
            }  
        }).start();  
    }
}
