from datetime import date

def meetup_day(year, month, dday, week):

	days = ['Monday', 'Tuesday', 'Wednesday',
				'Thursday', 'Friday', 'Saturday', 'Sunday']
	weeks = ['1st', '2nd', '3rd', '4th', 'last']

	# Determine the correct range
	if week == 'teenth':
		day_range = range( 13, 13+len(days) )
	else:
		w = weeks.index(week)
		day_range = range( len(days) * w + 1, len(days) * w + 8 )

	for day in day_range:
		if days.index(dday) == date(year, month, day).weekday():
			return date(year, month, day)
