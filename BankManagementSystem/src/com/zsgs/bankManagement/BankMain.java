package com.zsgs.bankManagement;

import java.io.IOException;

import com.zsgs.bankManagement.features.signin.SigninView;
import com.zsgs.bankManagement.features.signup.SigUpView;

public class BankMain {
//mysql -u root -p
	public static final int VERSION = 1;
	public static final String VERSION_Name = "1.0.0";

	public static void main(String[] args) throws IOException {
		System.out.println("<<<----------Version " + VERSION_Name + "---------->>>");
		//new SigUpView().init();
		new SigninView().init();
		//ConsoleInput.getScanner();
		//System.out.println(ParseHelper.calculateAgeYears(ParseHelper.parseDate(ConsoleInput.getScanner().nextLine())));
		//new DepositView().init();
        //new AllHistoryView().init(); 
	}
}
//		DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("hh:mm:ss a");
//		String time=LocalTime.now().format(FORMATTER),
//				date=LocalDate.now().toString();
//        System.out.println(time+" "+date);
		
//		new ActivationORDeactivationView(new ManagerHomeView()).init();
		
		
//		new AccountCreationView(new ManagerHomeView()).init();
//   new DetailView(new ManagerHomeView()).init();
		
//		List<Integer> a=new ArrayList<>();
//		
//		System.out.println(BankDB.getInstanse().getCustomerInfo().isEmpty());