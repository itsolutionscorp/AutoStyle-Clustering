from datetime import date

def add_gigasecond(birthdate):
	monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
	day = birthdate.day
	month = birthdate.month
	year = birthdate.year
	seconds = 10**9
	while seconds >= 3600*24:
		if leapyear(year):
			monthLength[1]=29
		else:
			monthLength[1]=28
		day += 1
		if day > monthLength[month-1]:
			day = 1
			month += 1
			if month > 12:
				month = 1
				year += 1
		seconds -= 3600*24
	return date(year, month, day)

def leapyear(year):
	if year%4 == 0 and (year%100 != 0 or year%400 == 0):
		return True
	return False
