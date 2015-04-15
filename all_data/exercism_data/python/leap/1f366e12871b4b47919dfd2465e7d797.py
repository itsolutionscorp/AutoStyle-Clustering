#Leap Year module 

def is_leap_year(year):
	if year % 4 == 0 and year % 100 != 0:
		return True
	return year % 400 == 0
