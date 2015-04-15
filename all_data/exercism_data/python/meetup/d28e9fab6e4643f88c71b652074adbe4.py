import datetime

def meetup_day(year, month, dayOWeek, identifier):
	day = 7
	monthStart = datetime.date(year, month, 1).weekday()
	dayNum = {'Sunday':0, 'Monday':1, 'Tuesday':2, 'Wednesday':3, 'Thursday':4, 'Friday':5, 'Saturday':6}
	weekDay = dayNum[dayOWeek]
	if weekDay > monthStart:
		day = weekDay - monthStart
	elif weekDay < monthStart:
		day = 7 - (monthStart - weekDay)
	if identifier == "teenth":
		while (day < 13):
			day += 7
	elif identifier == "2nd":
		day += 7
	elif identifier == "3rd":
		day += 14
	elif identifier == "4th":
		day += 21
	elif identifier == "last":
		#length of each month to gauge the "last" weekday of each month
		monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
		#gauge for a leap year
		if leapyear(year):
			monthLength[1]=29
		while day < monthLength[month-1]-6:
			day += 7
	return datetime.date(year, month, day)


def leapyear(year):
	if year%4 == 0 and (year%100 != 0 or year%400 == 0):
		return True
	return False

# Month starts on a Friday, looking for a Tuesday
'''Friday = 1
Sat = 2
Sun = 3
Mon = 4
Tues = 5'''
# Month starts on a Tuesday, looking for a Friday
'''Tues = 1
Wed = 2
Thurs = 3
Friday = 4'''
