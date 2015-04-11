from datetime import date
def meetup_day(year, month, weekday, when):

	weekdays = ['Monday', 'Tuesday', 'Wednesday',
	'Thursday', 'Friday', 'Saturday', 'Sunday']

	if when == '1st':
		w = 1
	elif when == '2nd':
		w = 2
	elif when == '3rd':
		w = 3
	elif when == '4th':
		w = 4
	elif when == 'last':
		w = 5
	else:
		# 13 - 19 (teenth)
		for day in range(13, 20):
			if weekdays.index(weekday) == date(year, month, day).weekday():
				return date(year, month, day)


	firstday = date(year, month, 1).weekday()
	for day in range(1 + 7*w - 7, 7*w+1):
		if weekdays.index(weekday) == date(year, month, day).weekday():
			return date(year, month, day)
