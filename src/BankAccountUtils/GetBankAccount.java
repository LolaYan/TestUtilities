package BankAccountUtils;

public class GetBankAccount {

	public static void main(String[] args) {
		if (args.length > 0) 
		{

			System.out.println("Please input BankAccountBase Length Setting - 7 or 8, Bank Suffix Setting - R4, or R3, or 000, or 0000. If you want.");
			System.out.println("Default BankAccountBase Length Setting is 7, Default Bank Suffix Setting is 000.");
			if(args[0].equals("7") || args[0].equals("8"))
				BankAccountGenerator.accountBaseLengthSetting = args[0];
			if(args[1].equals("R4") || args[1].equals("R3")|| args[1].equals("000")|| args[1].equals("0000"))
				BankAccountGenerator.suffixSetting = args[1];
		}
		
		// TODO Auto-generated method stub
		String accountStr = BankAccountGenerator.getBankAccount();
		String accountStrA = BankAccountGenerator.getBankAccount("A");
		String accountStrB = BankAccountGenerator.getBankAccount("B");
		String accountStrD = BankAccountGenerator.getBankAccount("D");
		String accountStrE = BankAccountGenerator.getBankAccount("E");
		String accountStrF = BankAccountGenerator.getBankAccount("F");
		String accountStrG = BankAccountGenerator.getBankAccount("G");
		String accountStrX = BankAccountGenerator.getBankAccount("X");
	}

}
