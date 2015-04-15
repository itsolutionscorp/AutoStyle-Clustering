from datetime import date, timedelta

weekdays = {
	'Monday': 0,
	'Tuesday': 1,
	'Wednesday': 2,
	'Thursday': 3,
	'Friday': 4,
	'Saturday': 5,
	'Sunday': 6,
}
def meetup_day(year, month, weekday, which):
	wd = weekdays[weekday]
	
	if which == '1st':
		d = date(year, month, 1)
	elif which == '2nd':
		d = date(year, month, 8)
	elif which == '3rd':
		d = date(year, month, 15)
	elif which == '4th':
		d = date(year, month, 22)
	elif which == 'last':
		month += 1
		if month == 13:
			month = 1
			year += 1
		d = date(year, month, 1) - timedelta(days = 7)
	elif which == 'teenth':
		d = date(year, month, 13)
		
	return d + timedelta(days = (wd - d.weekday()) % 7)
