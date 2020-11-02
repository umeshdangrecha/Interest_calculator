package com.umesh.patidar.interestcalculator;


public class DateDifferenceCustom
{
	private static int daysInMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private int diffYear = 0;
	private int diffMonth = 0;
	private int diffDay = 0;
	private String d1;
	private String d2;

	public DateDifferenceCustom(String d1, String d2)
	{
		this.d1 = d1;
		this.d2 = d2;
		dateDifference();
	}


	private static boolean checkLeapYear(int year)
	{
		return (((year % 4 == 0) && (year % 100 != 0)) ||
				(year % 400 == 0));
	}

	private void dateDifference()
	{
		String str1[] = d1.split("/");
		String str2[] = d2.split("/");

		int year1 = Integer.parseInt(str1[2]);
		int year2 = Integer.parseInt(str2[2]);

		int day1 = 0;
		int day2 = 0;
		int month1 = 0;
		int month2 = 0;

		/**
		 * if year 2 is greater than year 1 than swap the year's
		 * and also take day's and months in reverse order so that we have
		 * get better output
		 */
		if (year2 > year1)
		{
			System.out.println("year 2 grater");
			day2 = Integer.parseInt(str1[0]);
			day1 = Integer.parseInt(str2[0]);

			month2 = Integer.parseInt(str1[1]);
			month1 = Integer.parseInt(str2[1]);

			year1 = year1 ^ year2;
			year2 = year1 ^ year2;
			year1 = year1 ^ year2;

		} else
		{
			day1 = Integer.parseInt(str1[0]);
			day2 = Integer.parseInt(str2[0]);

			month1 = Integer.parseInt(str1[1]);
			month2 = Integer.parseInt(str2[1]);
		}

		if (year1 > year2)
		{
			diffYear = year1 - year2;

			if (month1 > month2)
			{
				diffMonth = month1 - month2;

				if (day1 > day2)
				{
					diffDay = day1 - day2;

				} else if (day2 > day1)
				{
					diffMonth--;
					int daysInLastMonth = daysInMonth[month1 - 2];
					if (daysInLastMonth == 28)
					{
						if (checkLeapYear(year1))
						{
							daysInLastMonth = 29;
						}
					}
					diffDay = daysInLastMonth - day2 + day1;
				} else
				{
					diffDay = 0;
				}

			}
			/**
			 * 15/05/2021 - 20/06/2020
			 * 11m 25d
			 */
			else if (month2 > month1)
			{
				diffYear--;
				diffMonth = 12 - (month2 - month1);

				if (day1 > day2)
				{
					diffDay = day1 - day2;

				} else if (day2 > day1)
				{
					diffMonth--;
					int daysInLastMonth = daysInMonth[month1 - 2];
					if (daysInLastMonth == 28)
					{
						if (checkLeapYear(year1))
						{
							daysInLastMonth = 29;
						}
					}
					diffDay = daysInLastMonth - day2 + day1;
				} else
				{
					diffDay = 0;
				}

			} else
			{
				diffMonth = 0;
				if (day1 > day2)
				{
					diffDay = day1 - day2;
				} else if (day2 > day1)
				{
					diffYear--;
					diffMonth = 11;
					int daysInLastMonth = daysInMonth[month1 - 2];
					if (daysInLastMonth == 28)
					{
						if (checkLeapYear(year1))
						{
							daysInLastMonth = 29;
						}
					}
					diffDay = daysInLastMonth - day2 + day1;
				} else
				{
					diffDay = 0;
				}
			}

		} else
		{
			diffYear = 0;
			if (month2 > month1)
			{
				month1 = month2 ^ month1;
				month2 = month2 ^ month1;
				month1 = month2 ^ month1;

				day1 = day1 ^ day2;
				day2 = day1 ^ day2;
				day1 = day1 ^ day2;
			}

			/**
			 * year is same
			 * but months are not same...
			 *
			 * first if-> 15/5/2020 - 10/4/2020
			 * second if -> 15/5/2020 - 20/4/2020
			 */
			if (month1 > month2)
			{
				diffMonth = month1 - month2;

				if (day1 > day2)
				{
					diffDay = day1 - day2;

				} else if (day2 > day1)
				{
					//diff month = 1
					diffMonth--; // diff month = 0
					int daysInLastMonth = daysInMonth[month1 - 2]; //month1 = 5,5-2=3, dilm = 30
					if (daysInLastMonth == 28)
					{
						if (checkLeapYear(year1))
						{
							daysInLastMonth = 29;
						}
					}
					diffDay = daysInLastMonth - day2 + day1;
				} else
				{
					diffDay = 0;
				}

			}
			/**
			 * Month and year are same
			 */
			else
			{
				diffMonth = 0;
				diffDay = day1 - day2;
				if (diffDay < 0)
					diffDay *= -1;
			}
		}

		System.out.println("Diff Year : " + diffYear);
		System.out.println("Diff Month : " + diffMonth);
		System.out.println("Diff Day : " + diffDay);

	}

	public int getDiffYear()
	{
		return diffYear;
	}

	public int getDiffMonth()
	{
		return diffMonth;
	}

	public int getDiffDay()
	{
		return diffDay;
	}
}
