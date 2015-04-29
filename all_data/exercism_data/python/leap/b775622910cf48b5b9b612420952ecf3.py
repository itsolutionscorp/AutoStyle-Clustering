# This program contains the is_leap_year function, which determines if a year is a leap year

def is_leap_year(year):
	return ( year % 4 ==0 and year % 100 != 0) or year % 400 ==0
