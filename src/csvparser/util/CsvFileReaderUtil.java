package csvparser.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * com.eBay.batch.utils_CsvFileReader.java
 * </pre>
 * @date : 2019. 6. 5. ���� 1:50:15
 * @author : hychoi
 */
public class CsvFileReaderUtil {
	/**
	 * <pre>
	 * 1. ���� : csvFileRead ������ �д� �޼���
	 * 2. ó������ : ���ϰ�θ� �̿��Ͽ� csvFile �� �о� ������ ����� �ش� ����� ���� map�� return �� 
	 * </pre>
	 * @Method Name : csvFileRead
	 * @date : 2019. 6. 5.
	 * @author : hychoi
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	������				�ۼ���						���泻��  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 5.		hychoi				���� �ۼ� 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param csvFilePath
	 * @return Map<String, String>
	 */ 	
	public static Map<String, String> csvFileRead(String csvFilePath) {

		Map<String, String> map = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		// TODO accept Reader or maybe InputStream rather than file path
	    File f = new File(csvFilePath);
	    
	    int headerSize = 0;
	    try (BufferedReader br = new BufferedReader(
	    		  new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8));) {
	    	// get the header fields
	        String line = br.readLine();
	        
	        if (!line.isEmpty()) {
	        	
	        	String[] headers = line.split(",");
		        headerSize = headers.length;
		        // iterate through record fields
		        while ((line = br.readLine()) != null) {
		        	sb.append(line);
		        	sb.append(System.lineSeparator());
		        }
	        }
	        
	    } catch (IOException e) {
	        // TODO trouble reading the file
	    } catch (IllegalArgumentException e) {
	        // TODO error while parsing the file
	    }
	    map.put("input", sb.toString());
	    map.put("headerSize",String.valueOf(headerSize));
	        
		return map;
	}
	
	/**
	 * <pre>
	 * 1. ���� : 
	 * 2. ó������ : 
	 * </pre>
	 * @Method Name : csvFileParse
	 * @date : 2019. 6. 5.
	 * @author : hychoi
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	������				�ۼ���						���泻��  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 5.		hychoi				���� �ۼ� 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param input
	 * @param separator
	 * @param quote
	 * @param headerSize
	 * @param dtoClassName
	 * @return
	 */ 	
	public static List<String[]> csvFileParse(String input, char separator, char quote, int headerSize) {
		
		String[] fields = new String[headerSize];
        List<String[]> list = new ArrayList<String[]>();
        final StringBuilder sb = new StringBuilder();
        
        int cnt = 0;
        boolean quoted=false;
        
        for (int i = 0, len= input.length(); i < len; i++) {
        	
            final char c = input.charAt(i);
            
            if (c == quote) {
            	//2xdouble quote in quoted
            	if( quoted   && i<len-1 && input.charAt(i+1) == quote ) { 
                    sb.append(c);
                    //skip
                    i++;
                }else {
                    
                	if (quoted) {
                        //���� �ɹ��� �ݵ�� separator �Ǵ� EOL(See RFC 4180)
                        if (i==len-1 || input.charAt(i+1) == separator) {
                            quoted = false;
                            continue;
                        }
                    } else {
                    	//not quoted
                        if (sb.length()==0){
                            quoted=true;
                            continue;
                        }
                    }
                	// ���� �ʵ尡 double quotes�� ������ ������ ū ����ǥ�� �ʵ� �ȿ� ��Ÿ���� ���� �� ����
                    sb.append(c);                   
                }
            } else if (c == separator && !quoted) {
            	// quoted ���°� �ƴϰ� �����ڸ� �����ٸ� �ʵ忡 ���� �� StringBuilder �ʱ�ȭ
            	fields[cnt] = sb.toString();
                sb.setLength(0);
                cnt++;                
            } else {
            	/* �ƹ��͵� �ƴҶ� */
                sb.append(c);
                
                /* ������ �ʵ� �� ������ �ٲ��ִ� �۾�
                 * ��� ī��Ʈ�� üũ�� �ϰ� 
                 */
                if (cnt == headerSize-1) {
                	if ( sb.toString().endsWith("\r\n")) {
                		fields[cnt] = sb.toString();
                		list.add(fields);
                    	cnt = 0;
                    	fields = new String[headerSize];
                    	sb.setLength(0);
                	}
                }
            }
        }
        return list;
	}
}
