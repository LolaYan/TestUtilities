package Excel;

public class BankAccount {
	private String bankAccount;
	private String bankCode;
	private String accountBase;
	private String bankSuffix;
	private String email;
	private String result;
	private String resultMsg;
	

	public BankAccount(String bankAccount, String bankCode, String accountBase,
			String bankSuffix, String email, String result, String resultMsg) {
		super();
		this.bankAccount = bankAccount;
		this.bankCode = bankCode;
		this.accountBase = accountBase;
		this.bankSuffix = bankSuffix;
		this.email = email;
		this.result = result;
		this.resultMsg = resultMsg;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankSuffix() {
		return bankSuffix;
	}

	public void setBankSuffix(String bankSuffix) {
		this.bankSuffix = bankSuffix;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getAccountBase() {
		return accountBase;
	}

	public void setAccountBase(String accountBase) {
		this.accountBase = accountBase;
	}

	@Override
	public String toString() {
		return "BankAccount [bankAccount=" + bankAccount + ", bankCode="
				+ bankCode + ", accountBase=" + accountBase + ", bankSuffix="
				+ bankSuffix + ", email=" + email + ", result=" + result
				+ ", resultMsg=" + resultMsg + "]";
	}
	
}
