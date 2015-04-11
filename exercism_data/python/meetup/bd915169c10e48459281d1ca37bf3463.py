from datetime import date, timedelta

def meetup_day(year, month, day_of_week, week):
	weekday_dict = {
		'Monday': 0,
		'Tuesday': 1,
		'Wednesday': 2,
		'Thursday': 3,
		'Friday': 4,
		'Saturday': 5,
		'Sunday': 6
	}
	starting_day = date(year=year, month=month, day=1)
	
	days_til_day_of_week = weekday_dict[day_of_week] - starting_day.weekday()
	print weekday_dict[day_of_week], starting_day.weekday()
	if starting_day.weekday() > weekday_dict[day_of_week]:
		days_til_day_of_week += 7

	if week is '1st':
		return starting_day + timedelta(days=days_til_day_of_week)
	elif week is '2nd':
		return starting_day + timedelta(days=days_til_day_of_week, weeks=1)
	elif week is '3rd':
		return starting_day + timedelta(days=days_til_day_of_week, weeks=2)
	elif week is '4th':
		return starting_day + timedelta(days=days_til_day_of_week, weeks=3)
	elif week is 'teenth':
		for i in xrange(13,19,1):
			if date(year=year, month=month, day=i).weekday() is weekday_dict[day_of_week]:
				return date(year=year, month=month, day=i)
	elif week is 'last':
		for i in xrange((date(year=year, month=month+1, day=1) - timedelta(days=1)).day,1,-1):
			if date(year=year, month=month, day=i).weekday() is weekday_dict[day_of_week]:
				return date(year=year, month=month, day=i)

