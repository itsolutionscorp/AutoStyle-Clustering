# This program contains the is_leap_year function, which determines if a year is a leap year

def is_leap_year(year):
	return False if year % 4 !=0 or (year % 100 == 0 and year % 400 !=0) else True
