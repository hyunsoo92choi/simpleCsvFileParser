package csvparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import csvparser.dto.SampleDto;
import csvparser.util.CsvFileReaderUtil;

public class CSVExample {
	
	public static void main(String[] args) throws  Exception {
		
		String csvFile = "C:\\Users\\hychoi\\Documents\\Yahoo.csv";
		
		Map<String, String> map = CsvFileReaderUtil.csvFileRead("C:\\Users\\hychoi\\Documents\\Yahoo.csv");
		String input = map.get("input").toString();
		Integer headerCnt = Integer.valueOf(map.get("headerSize"));
		List<String[]> records = CsvFileReaderUtil.csvFileParse(input,',', '"', headerCnt);
		List<SampleDto> result = new ArrayList<SampleDto>();
		
		for (String[] strings : records) {
			result.add(new SampleDto(strings));
		}
		System.out.println("result: " + result.toString());
	}
}
