from datetime import date
import calendar

def dayName(date_):
    return date_.strftime('%A')


def meetup_day(year, month, dayOfWeek, expression):
	# teenth is 13, 14, 15, 16, 17, 18, 19
	# 1st is first week
	# 2nd is second week
	# 3rd is third week
	# 4th is fourth week
	# last is last week 
	# days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday',
	# 		'Friday', 'Saturday', 'Sunday']
	# weekdayAsDigit = days.index(day)
	MonthLength = calendar.monthrange(year, month)[1]
	daysInMonth = (date(year, month, day) for day in range(1, MonthLength+1))
	shortlist = [date_ for date_ in daysInMonth if dayName(date_) == dayOfWeek]
	print(shortlist)

	if expression == 'teenth':
		return next(d for d in shortlist if 13<= d.day <= 19)
	if expression == 'last':
		return shortlist[-1]
	return shortlist[int(expression[0]) - 1]
