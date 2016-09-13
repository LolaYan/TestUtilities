package BankAccountUtils;

public class BankAccountGenerator extends BankAccountBase 
{


    //getBankAccount
    public static String getBankAccount()
    {
        //1. get BankID
        BankIDStr = getBankID();
        //2. get BankBranch
        //Based on BankID, filter the AlgorithmType of D, E, E, G, X, F
        BankBranchStr = getBankBranch(BankIDStr);
        doMod();
        System.out.println("BankIDStr: " + BankIDStr);
        System.out.println("BankBranchStr: " + BankBranchStr);
        System.out.println("BankAccountBaseStr: " + BankAccountBaseStr);
        System.out.println("suffixStr: " + suffixStr);
        System.out.println("AlgorithmType: " + AlgorithmType);
        //System.out.println("algorithmWeight: " + String.Join(",", algorithmWeight));
        System.out.println("AlgorithmMod: " + AlgorithmMod);
        System.out.println("checkDigit: " + checkDigit);
        System.out.println("BankAccountStr: " + BankIDStr + "-" + BankBranchStr + "-" + BankAccountBaseStr + "-" + suffixStr);
        System.out.println("BankAccountStr: " + BankAccountStr);
        System.out.println("BankAccountStr length: " + BankAccountStr.length());
        System.out.println("");
        return BankAccountStr;
    }

    public static String getBankAccountA()
    {
        //1. get BankID
        BankIDStr = getBankID_AB();
        //2. get BankBranch
        //Based on BankID, filter the AlgorithmType of D, E, E, G, X, F
        BankBranchStr = getBankBranch(BankIDStr);
        
        suffixStr = PaddingLeftWithZero(RandomNumber(0, 9999), 4);
        AlgorithmType = "A";
        do
        {
            BankAccountBaseStr = PaddingLeftWithZero(RandomNumber(0, 98999), 7);
            String tempBankAccountStr = BankIDStr + BankBranchStr + BankAccountBaseStr;
            algorithmWeight = GetAlgorithmWeight(AlgorithmType);
            checkDigit = GetCheckDigit_Mod11(AlgorithmType, tempBankAccountStr, algorithmWeight);                
        } while (checkDigit.equals("N/A"));
        BankAccountBaseStr = BankAccountBaseStr + checkDigit;
        checkSuffixSetting();
        checkAccountBaseLengthSetting();
        BankAccountStr = BankIDStr + BankBranchStr + BankAccountBaseStr + suffixStr;
        //printInfo();
        return BankAccountStr;
    }

    public static String getBankAccountB()
    {
        //1. get BankID
        BankIDStr = getBankID_AB();
        //2. get BankBranch
        //Based on BankID, filter the AlgorithmType of D, E, E, G, X, F
        BankBranchStr = getBankBranch(BankIDStr);

        suffixStr = PaddingLeftWithZero(RandomNumber(0, 9999), 4);
        AlgorithmType = "B";
        do
        {
            BankAccountBaseStr = PaddingLeftWithZero(RandomNumber(99000, 999999), 7);
            String tempBankAccountStr = BankIDStr + BankBranchStr + BankAccountBaseStr;
            algorithmWeight = GetAlgorithmWeight(AlgorithmType);
            checkDigit = GetCheckDigit_Mod11(AlgorithmType, tempBankAccountStr, algorithmWeight);
        } while (checkDigit.equals("N/A"));
        BankAccountBaseStr = BankAccountBaseStr + checkDigit;
        checkSuffixSetting();
        checkAccountBaseLengthSetting();
        BankAccountStr = BankIDStr + BankBranchStr + BankAccountBaseStr + suffixStr;
        //printInfo();
        return BankAccountStr;
    }

    public static String getBankAccount(String type)
    {
        switch (type)
        {
            case "A":
                BankAccountStr = getBankAccountA();
                break;
            case "B":
                BankAccountStr = getBankAccountB();
                break;
            default:
                BankIDStr = getBankID(type);
                BankBranchStr = getBankBranch(BankIDStr);
                doMod();
                break;
        }
           printInfo();
        return BankAccountStr;
    }

}
