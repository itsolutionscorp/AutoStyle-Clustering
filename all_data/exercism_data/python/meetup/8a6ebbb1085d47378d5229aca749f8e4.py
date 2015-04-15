import datetime

def meetup_day(year, month, weekday, position):
	weekday = weekdays[weekday.lower()]
	possible_dates = [date for date in 
						dates_for_month(year, month)
						if date.weekday() is weekday]

	special_cases = {
			'teenth': filter(lambda d: 12 < d.day < 20, 
						possible_dates)[0],
			'last': possible_dates[-1]
			}

	if position in special_cases:
		return special_cases[position]
	else:
		return possible_dates[int(position[0]) - 1]

def dates_for_month(year, month):
	'''
	Generates dates for the year/month
	'''	
	date = datetime.date(year, month, 1)
	while date.month is month:
		yield date
		date += datetime.timedelta(days = 1)

weekdays = {
	'monday': 0, 'tuesday': 1, 'wednesday': 2,
	'thursday': 3, 'friday': 4, 'saturday': 5,
	'sunday': 6 
	}
