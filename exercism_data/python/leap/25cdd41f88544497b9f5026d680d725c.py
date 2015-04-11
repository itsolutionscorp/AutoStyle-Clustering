#year.py
#Determine if given year is leapyear

def is_leap_year(year):
	if year % 4 == 0:
		if year % 400 == 0:
			return True
		if year % 100 == 0:
			return False
		return True
