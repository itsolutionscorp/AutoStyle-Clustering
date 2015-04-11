from datetime import date

def meetup_day(meetup_year, meetup_month, meetup_dayofweek, meetup_frequency):
	meetup_date = date(meetup_year, meetup_month, 1)
	meetup_dayofweekint = {
		'Monday'    : 0,
		'Tuesday'   : 1,
		'Wednesday' : 2,
		'Thursday'  : 3,
		'Friday'    : 4,
		'Saturday'  : 5,
		'Sunday'	: 6,
		}.get(meetup_dayofweek, 7)
	while (meetup_date.weekday() != meetup_dayofweekint):
		meetup_date = meetup_date.replace(day = meetup_date.day + 1)	
	if meetup_frequency == '2nd':
		meetup_date = meetup_date.replace(day = meetup_date.day + 7)
	elif meetup_frequency == '3rd':
		meetup_date = meetup_date.replace(day = meetup_date.day + 14)
	elif meetup_frequency == '4th':
		meetup_date = meetup_date.replace(day = meetup_date.day + 21)
	elif meetup_frequency == 'teenth':
		while (meetup_date.day < 12):
			meetup_date = meetup_date.replace(day = meetup_date.day + 7)
	elif meetup_frequency == 'last':
		try:
			while (meetup_date.day < 31):
				meetup_date = meetup_date.replace(day = meetup_date.day + 7)
		except ValueError:
			pass
	return meetup_date
