import datetime

def meetup_day(year, month, weekday, suffix):
	weekday = weekdays[weekday.lower()]
	dates = [date for date in dates_for_month(year, month)
				if date.weekday() is weekday] # Get all weekdays 

	if suffix == 'last':
		return dates[-1]
	elif suffix == 'teenth':
		return filter(lambda d: d.day >= 13 and d.day <= 19, dates)[0]
	else:
		week = int(suffix[0])
		return dates[week - 1]

def dates_for_month(year, month):
	'''
	Generates dates for the year/month
	'''	
	date = datetime.date(year, month, 1)
	while date.month is month:
		yield date
		date += datetime.timedelta(days = 1)

weekdays = {
	'monday': 0,
	'tuesday': 1,
	'wednesday': 2,
	'thursday': 3,
	'friday': 4,
	'saturday': 5,
	'sunday': 6
}
