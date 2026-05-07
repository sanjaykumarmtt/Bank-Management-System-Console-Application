package com.zsgs.bankManagement.features.transactions.deposit;

class DepositPresenter implements IDepositPresenter,IDepositMadeltoPresenter {

	IDepositModel iDepositModel;
	IDepositView iDeposiView;

	public DepositPresenter(IDepositView iDeposiView) {
		this.iDeposiView = iDeposiView;
		this.iDepositModel = new DepositModel(this);
	}

	@Override
	public void init(String accountNumber) {
		iDepositModel.init(accountNumber);
	}

	@Override
	public String depositBalance(double depositAmount) {
		return iDepositModel.depositBalance(depositAmount);
	}
	
	@Override
	public boolean isOnlyDigits(String depositAmount) {
		return iDepositModel.isOnlyDigits(depositAmount);
	}


	@Override
	public void depositAmont() {
		iDeposiView.depositAmont();
	}

	@Override
	public void showErrorMessage(String message) {
		iDeposiView.showErrorMessage(message);
	}

	
}