from datetime import date

def add_gigasecond(birthdate):
	monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
	day = birthdate.day
	month = birthdate.month
	year = birthdate.year
	seconds = 10**9
	while seconds >= 3600*24*365:
		if (month > 2 and leapyear(year)) or (month < 3 and leapyear(year+1)):
			seconds -= 3600*24
		year += 1
		seconds -= 3600*24*365
	while seconds >= 3600*24*monthLength[month-1]:
		month += 1
		if month > 12:
			month = 1
			year += 1
		seconds -= 3600*24*monthLength[month-1]
	while seconds >= 3600*24:
		day += 1
		if day > monthLength[month-1] and not(leapmonth(month,year)):
			day = 1
			month += 1
			if month > 12:
				month = 1
				year += 1
		elif day > monthLength[month-1]+1 and leapmonth(month,year):
			day = 1
			month +=1
			if month > 12:
				month = 1
				year += 1
		seconds -= 3600*24
	print seconds
	print year, month, day
	return date(year, month, day)

def leapyear(year):
	if year%4 == 0 and (year%100 != 0 or year%400 == 0):
		return True
	return False

def leapmonth(month, year):
	if month == 2 and leapyear(year):
		return True
	return False
