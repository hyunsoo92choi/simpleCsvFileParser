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
 * @date : 2019. 6. 5. 오후 1:50:15
 * @author : hychoi
 */
public class CsvFileReaderUtil {
	/**
	 * <pre>
	 * 1. 개요 : csvFileRead 내용을 읽는 메서드
	 * 2. 처리내용 : 파일경로를 이용하여 csvFile 을 읽어 파일의 내용과 해더 사이즈를 담은 map을 return 함 
	 * </pre>
	 * @Method Name : csvFileRead
	 * @date : 2019. 6. 5.
	 * @author : hychoi
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 5.		hychoi				최초 작성 
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
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : csvFileParse
	 * @date : 2019. 6. 5.
	 * @author : hychoi
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 5.		hychoi				최초 작성 
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
                        //다음 심벌은 반드시 separator 또는 EOL(See RFC 4180)
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
                	// 만약 필드가 double quotes로 묶이지 않으면 큰 따옴표가 필드 안에 나타나지 않을 수 있음
                    sb.append(c);                   
                }
            } else if (c == separator && !quoted) {
            	// quoted 상태가 아니고 구분자를 만났다면 필드에 저장 후 StringBuilder 초기화
            	fields[cnt] = sb.toString();
                sb.setLength(0);
                cnt++;                
            } else {
            	/* 아무것도 아닐때 */
                sb.append(c);
                
                /* 마지막 필드 후 라인을 바꿔주는 작업
                 * 계속 카운트는 체크를 하고 
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
