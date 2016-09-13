package BankAccountUtils;

import java.text.DecimalFormatSymbols;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class BankAccountValidator  extends BankAccountBase  
{
	protected static String[] BankIDArr = { "01", "02", "03", "06", "08", "09", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "33", "35", "38" };
    public static String ActualCheckDigit = "UNKOWN";
    public static String ExpectCheckDigit = "UNKOWN";   

    public static Boolean validateBankID(String BankID)
    {
        if(ArrayUtils.contains( BankIDArr, BankID))
        {
            return true ;
        }
        else
        {
            return false ;
        }
    }

    public static Boolean validateBankBranch(String BankIDStr, String BankBranchStr)
    {
        int BankBranch = Integer.parseInt(BankBranchStr);
        Boolean result;
        AlgorithmType = "UNKOWN";
        switch (BankIDStr)
        {
            case "01":
                if ((BankBranch >= 1 && BankBranch <= 999) || (BankBranch >= 1100 && BankBranch <= 1199) || (BankBranch >= 1800 && BankBranch <= 1899))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "02":
                if ((BankBranch >= 1 && BankBranch <= 999) || (BankBranch >= 1200 && BankBranch <= 1299))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "03":
                if ((BankBranch >= 1 && BankBranch <= 999) || (BankBranch >= 1300 && BankBranch <= 1399) || (BankBranch >= 1500 && BankBranch <= 1599) || (BankBranch >= 1700 && BankBranch <= 1799) || (BankBranch >= 1900 && BankBranch <= 1999))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "06":
                if ((BankBranch >= 1 && BankBranch <= 999) || (BankBranch >= 1400 && BankBranch <= 1499))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "08":
                if ((BankBranch >= 6500 && BankBranch <= 6599) || (BankBranch >= 1400 && BankBranch <= 1499))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                AlgorithmType = "D";
                break;
            case "09":
                if (BankBranch == 0)
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                AlgorithmType = "E";
                break;
            case "11":
                if ((BankBranch >= 5000 && BankBranch <= 6499) || (BankBranch >= 6600 && BankBranch <= 8999))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "12":
                if ((BankBranch >= 3000 && BankBranch <= 3299) || (BankBranch >= 3400 && BankBranch <= 3499) || (BankBranch >= 3600 && BankBranch <= 3699))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "13":
                if ((BankBranch >= 4900 && BankBranch <= 4999))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "14":
                if ((BankBranch >= 4700 && BankBranch <= 4799))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "15":
                if ((BankBranch >= 3900 && BankBranch <= 3999))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "16":
                if ((BankBranch >= 4400 && BankBranch <= 4499))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "17":
                if ((BankBranch >= 3300 && BankBranch <= 3399))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "18":
                if ((BankBranch >= 3500 && BankBranch <= 3599))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "19":
                if ((BankBranch >= 4600 && BankBranch <= 4649))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "20":
                if ((BankBranch >= 4100 && BankBranch <= 4199))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "21":
                if ((BankBranch >= 4800 && BankBranch <= 4899))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "22":
                if ((BankBranch >= 4000 && BankBranch <= 4049))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "23":
                if ((BankBranch >= 3700 && BankBranch <= 3799))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "24":
                if ((BankBranch >= 4300 && BankBranch <= 4399))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "25":
                if ((BankBranch >= 2500 && BankBranch <= 2599))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                AlgorithmType = "F";
                break;
            case "26":
                if ((BankBranch >= 2600 && BankBranch <= 2699))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                AlgorithmType = "G";
                break;
            case "27":
                if ((BankBranch >= 3800 && BankBranch <= 3849))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "28":
                if ((BankBranch >= 2100 && BankBranch <= 2149))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                AlgorithmType = "G";
                break;
            case "29":
                if ((BankBranch >= 2150 && BankBranch <= 2299))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                AlgorithmType = "G";
                break;
            case "30":
                if ((BankBranch >= 2900 && BankBranch <= 2949))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "31":
                if ((BankBranch >= 2800 && BankBranch <= 2849))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                AlgorithmType = "X";
                break;
            case "33":
                if ((BankBranch >= 6700 && BankBranch <= 6799))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                AlgorithmType = "F";
                break;
            case "35":
                if ((BankBranch >= 2400 && BankBranch <= 2499))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            case "38":
                if ((BankBranch >= 9000 && BankBranch <= 9499))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
                break;
            default:
                result = false;
                break;

        }

        return result;
    }

    public static String getActualAlgorithmType(String BankIDStr, String BankBranchStr, String BankAccountBaseStr)
    {
        Boolean result;
        int BankAccountBase = Integer.parseInt(BankAccountBaseStr);
        if (validateBankID(BankIDStr) == true)
        {
            if(validateBankBranch(BankIDStr, BankBranchStr) == true)
            {
                if (AlgorithmType == "UNKOWN")
                {
                    if (BankAccountBase >= 0 && BankAccountBase < 990000)
                    {
                        AlgorithmType = "A";
                    }
                    else if (BankAccountBase >= 990000 && BankAccountBase <= 99999999)
                    {
                        AlgorithmType = "B";
                    }
                }
            }
        }
        return AlgorithmType;
    }

    public static Boolean validateBankAccountBase(String BankIDStr, String BankBranchStr, String BankAccountBase, String BankAccountSuffix)
    {
        validateBankBranch(BankIDStr, BankBranchStr);
        getActualAlgorithmType(BankIDStr, BankBranchStr, BankAccountBase);

        BankAccountBase = StringUtils.leftPad(BankAccountBase, 8, "0");
        BankAccountSuffix = StringUtils.leftPad(BankAccountSuffix, 4, "0");
        String tempBankAccountBase = BankAccountBase.substring(0, 7);
        String tempBankAccountSuffix = BankAccountSuffix.substring(0, 3);
        Boolean result;
        String tempBankAccountStr;
        //validate the length of BankAccountStr is 2+4+7 = 13
        // get AlgorithmWeight
        algorithmWeight = GetAlgorithmWeight(AlgorithmType);
        
        //System.out.println("AlgorithmType: "+AlgorithmType);
        if (AlgorithmType.equals("A") || AlgorithmType.equals("B") || AlgorithmType.equals("C") || AlgorithmType.equals("D"))
        {
            tempBankAccountStr = BankIDStr + BankBranchStr + tempBankAccountBase;
            ActualCheckDigit = BankAccountBase.substring(BankAccountBase.length() - 1);
            ExpectCheckDigit = GetCheckDigit_Mod11(AlgorithmType, tempBankAccountStr, algorithmWeight);
            //System.out.println("tempBankAccountStr: "+tempBankAccountStr);
            //System.out.println("ActualCheckDigit: "+ActualCheckDigit);
            //System.out.println("ExpectCheckDigit: "+ExpectCheckDigit);
        }
        else if (AlgorithmType.equals("E"))
        {
            tempBankAccountStr = BankIDStr + BankBranchStr + BankAccountBase + tempBankAccountSuffix;
            ActualCheckDigit = BankAccountSuffix.substring(BankAccountSuffix.length() - 1);
            ExpectCheckDigit = GetCheckDigit_Mod11(AlgorithmType, tempBankAccountStr, algorithmWeight);
        }
        else if (AlgorithmType.equals("F"))
        {
            tempBankAccountStr = BankIDStr + BankBranchStr + tempBankAccountBase;
            ActualCheckDigit = BankAccountBase.substring(BankAccountBase.length() - 1);
            ExpectCheckDigit = GetCheckDigit_Mod10(AlgorithmType, tempBankAccountStr, algorithmWeight);
        }
        else if (AlgorithmType.equals("G"))
        {
            tempBankAccountStr = BankIDStr + BankBranchStr + BankAccountBase + tempBankAccountSuffix;
            ActualCheckDigit = BankAccountSuffix.substring(BankAccountSuffix.length() - 1);
            ExpectCheckDigit = GetCheckDigit_Mod10(AlgorithmType, tempBankAccountStr, algorithmWeight);
        }
        else if (AlgorithmType.equals("X"))
        {
            ActualCheckDigit = "X";
            ExpectCheckDigit = "X";
        }

        if (ActualCheckDigit.equals(ExpectCheckDigit))
        {
            result = true;
        }
        else
        {
            result = false;
        }

        return result;
    }
	
    public static boolean isStringNumeric( String str )
    {
        DecimalFormatSymbols currentLocaleSymbols = DecimalFormatSymbols.getInstance();
        char localeMinusSign = currentLocaleSymbols.getMinusSign();

        if ( !Character.isDigit( str.charAt( 0 ) ) && str.charAt( 0 ) != localeMinusSign ) return false;

        boolean isDecimalSeparatorFound = false;
        char localeDecimalSeparator = currentLocaleSymbols.getDecimalSeparator();

        for ( char c : str.substring( 1 ).toCharArray() )
        {
            if ( !Character.isDigit( c ) )
            {
                if ( c == localeDecimalSeparator && !isDecimalSeparatorFound )
                {
                    isDecimalSeparatorFound = true;
                    continue;
                }
                return false;
            }
        }
        return true;
    }
    

	public static void main(String[] args) {
	}
}
