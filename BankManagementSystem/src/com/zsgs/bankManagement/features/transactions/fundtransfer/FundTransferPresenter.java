package com.zsgs.bankManagement.features.transactions.fundtransfer;

class FundTransferPresenter implements IFundTransferPresentertoModel,IFundtransferPresenter{

	private IFundTransferView iFundTransferView;
	private IFundTransFerModel iFundTransFerModel;
	
	
	public FundTransferPresenter(IFundTransferView iFundTransferView) {
		this.iFundTransferView = iFundTransferView;
		this.iFundTransFerModel=new FundTransferModel(this);
	}

	@Override
	public void init(String accountNumber) {
		
		iFundTransFerModel.init(accountNumber);
	}

	@Override
	public String numberValidation(String amountValidation) {
		return iFundTransFerModel.numberValidation(amountValidation);
	}

	@Override
	public String amountIsAvlabel(String amountValidation) {
		return iFundTransFerModel.amountIsAvlabel(amountValidation);
	}

	@Override
	public String receiverAccountIsAvlabel(String amountValidation) {
		
		return iFundTransFerModel.receiverAccountIsAvlabel(amountValidation);
	}

	
	@Override
	public void getAmounttoGive(String amount,String reAccountNumber) {
		iFundTransFerModel.getAmounttoGive(amount, reAccountNumber);
		
	}
	
	@Override
	public void validateAmountANDREAccountNumber() {
		iFundTransferView.validateAmountANDREAccountNumber();
	}
	
	@Override
	public void onSuccessFailed(String message) {
		iFundTransferView.onSuccessFailed(message);
	}

	@Override
	public void showErrorMessage(String message) {
		iFundTransferView.showErrorMessage(message);	
	}

}
