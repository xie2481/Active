public class LiXi {

	public static void main(String[] args) {
		double money = 100000;
		int day = 365 * 3;
		zhaoZhaoYing(money, day);
		bank(money, day);
	}

	public static void zhaoZhaoYing(double money, int day) {
		double rate = 0.7052;
		double accrual = 0;
		for (int i = 0; i < day; i++) {
			accrual += ((money + accrual) / 10000 * rate);
		}
		System.out.println(accrual);
	}

	public static void bank(double money, int day) {
		double rate = 0.0275;
		double accrual = money * rate * day / 365;
		System.out.println(accrual);
	}
}
