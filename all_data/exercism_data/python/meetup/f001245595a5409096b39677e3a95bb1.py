from datetime import date
from calendar import monthrange

def meetup_day(year, month, day, catagory):
	days = {'Monday':1, 'Tuesday':2, 'Wednesday':3, 'Thursday':4, 'Friday':5, 'Saturday':6, 'Sunday':7}
	total = monthrange(year, month)
	teen = [13, 14, 15, 16, 17, 18, 19]
	if catagory == 'teenth':
		for i in range(13, 20):
			testDate = date(year, month, i)
			if date.isoweekday(testDate) == days[day]:
				return date(year, month, i)
	elif catagory == '1st' or catagory == '2nd' or catagory == '3rd' or catagory == '4th' or catagory == 'last':
		nthDay = []
		for i in range(1, (total[1] + 1)):
			testDate = date(year, month, i)
			if date.isoweekday(testDate) == days[day]:
				nthDay.append(i)
		if catagory == '1st':
			return date(year, month, nthDay[0])
		elif catagory == '2nd':
			return date(year, month, nthDay[1])
		elif catagory == '3rd':
			return date(year, month, nthDay[2])
		elif catagory == '4th':
			return date(year, month, nthDay[3])
		elif catagory == 'last':
			return date(year, month, nthDay[-1])
	else:
		return 'Error, incorrect format'
