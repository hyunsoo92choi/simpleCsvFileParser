package csvparser.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class SampleDto {
	private  String path;
    private  String name;
    private  String code;
    private  String subCode;
    private  String originalPrice;
    private  String price;
    private  String salePrice;
    private  String options;
    private  String headline;
    private  String caption;
    private  String abstraction;
    private  String explanation;
    private  String additional1;
    private  String additional2;
    private  String additional3;
    private  String relevantLinks;
    private  String shipWeight;
    private  String taxable;
    private  String releaseDate;
    private  String temporaryPointTerm;
    private  String pointCode;
    private  String metaKey;
    private  String metaDesc;
    private  String template;
    private  String salePeriodStart;
    private  String salePeriodEnd;
    private  String saleLimit;
    private  String spCode;
    private  String prRate;
    private  String brandCode;
    private  String personCode;
    private  String yahooProductCode;
    private  String productCode;
    private  String jan;
    private  String isbn;
    private  String delivery;
    private  String astkCode;
    private  String condition;
    private  String taoJapan;
    private  String productCategory;
    private  String spec1;
    private  String spec2;
    private  String spec3;
    private  String spec4;
    private  String spec5;
    private  String spec6;
    private  String spec7;
    private  String spec8;
    private  String spec9;
    private  String spec10;
    private  String display;
    private  String sort;
    private  String spAdditional;
    private  String sortPriority;
    private  String originalPriceEvidence;
    private  String leadTimeInstock;
    private  String leadTimeOutstock;
    
    
    public SampleDto(String[] csvFields) {
        
        this.path = processStringField(csvFields[0]);
        this.name = processStringField(csvFields[1]);
        this.code = processStringField(csvFields[2]);
        this.subCode = processStringField(csvFields[3]);
        this.originalPrice = processStringField(csvFields[4]);
        this.price = processStringField(csvFields[5]);
        this.salePrice = processStringField(csvFields[6]);
        this.options = processStringField(csvFields[7]);
        this.headline = processStringField(csvFields[8]);
        this.caption = processStringField(csvFields[9]);
        this.abstraction = processStringField(csvFields[10]);
        this.explanation = processStringField(csvFields[11]);
        this.additional1 = processStringField(csvFields[12]);
        this.additional2 = processStringField(csvFields[13]);
        this.additional3 = processStringField(csvFields[14]);
        this.relevantLinks = processStringField(csvFields[15]);
        this.shipWeight = processStringField(csvFields[16]);
        this.taxable = processStringField(csvFields[17]);
        this.releaseDate = processStringField(csvFields[18]);
        this.temporaryPointTerm = processStringField(csvFields[19]);
        this.pointCode = processStringField(csvFields[20]);
        this.metaKey = processStringField(csvFields[21]);
        this.metaDesc = processStringField(csvFields[22]);
        this.template = processStringField(csvFields[23]);
        this.salePeriodStart = processStringField(csvFields[24]);
        this.salePeriodEnd = processStringField(csvFields[25]);
        this.saleLimit = processStringField(csvFields[26]);
        this.spCode = processStringField(csvFields[27]);
        this.prRate = processStringField(csvFields[28]);
        this.brandCode = processStringField(csvFields[29]);
        this.personCode = processStringField(csvFields[30]);
        this.yahooProductCode = processStringField(csvFields[31]);
        this.productCode = processStringField(csvFields[32]);
        this.jan = processStringField(csvFields[33]);
        this.isbn = processStringField(csvFields[34]);
        this.delivery = processStringField(csvFields[35]);
        this.astkCode = processStringField(csvFields[36]);
        this.condition = processStringField(csvFields[37]);
        this.taoJapan = processStringField(csvFields[38]);
        this.productCategory = processStringField(csvFields[39]);
        this.spec1 = processStringField(csvFields[40]);
        this.spec2 = processStringField(csvFields[41]);
        this.spec3 = processStringField(csvFields[42]);
        this.spec4 = processStringField(csvFields[43]);
        this.spec5 = processStringField(csvFields[44]);
        this.spec6 = processStringField(csvFields[45]);
        this.spec7 = processStringField(csvFields[46]);
        this.spec8 = processStringField(csvFields[47]);
        this.spec9 = processStringField(csvFields[48]);
        this.spec10 = processStringField(csvFields[49]);
        this.display = processStringField(csvFields[50]);
        this.sort = processStringField(csvFields[51]);
        this.spAdditional = processStringField(csvFields[52]);
        this.sortPriority = processStringField(csvFields[53]);
        this.originalPriceEvidence = processStringField(csvFields[54]);
        this.leadTimeInstock = processStringField(csvFields[55]);
        this.leadTimeOutstock = processStringField(csvFields[56]);
        
    }

    private static String processStringField(String field) {
        // consider empty fields as null
        if (field.isEmpty()) {
            return null;
        }
        // strip double quotes and replace any escaped quotes
        final int endIndex = field.length() - 1;
        if (field.charAt(0) == '"' && field.charAt(endIndex) == '"') {
            return field.substring(1, endIndex).replace("\"\"", "\"");
        }
        return field;
    }
}
