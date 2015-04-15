def is_leap_year(year):
#Takes year and determines whether or not the year is a leap year
	return year % 4 == 0 and (year % 400 == 0 or year % 100 != 0)
		
	
