package com.zsgs.bankManagement.features.transactions.withdraw;

class WithdrawPresenter implements IWithdrawPresenter,IWithdrawMadeltoPresenter{

	private IWithdrawModel iWithdrawModel;
	private IWithdrawView iWithdrawView;
	
	public WithdrawPresenter(IWithdrawView iWithdrawView) {
		this.iWithdrawModel = new WithdrawModel(this);
		this.iWithdrawView = iWithdrawView;
	}

	@Override
	public void init(String accountNumber) {
		iWithdrawModel.init(accountNumber);
	}

	@Override
	public boolean isOnlyDigits(String withrawAmount) {
		return iWithdrawModel.isOnlyDigits(withrawAmount);
	}

	@Override
	public String withdraw(double withrawAmount) {
		return iWithdrawModel.withrawMone(withrawAmount);
	}

	@Override
	public void withdrawAmont() {
		iWithdrawView.withdrawAmont();	
	}

	@Override
	public void showErrorMessage(String message) {
		iWithdrawView.showErrorMessage(message);
	}
}
