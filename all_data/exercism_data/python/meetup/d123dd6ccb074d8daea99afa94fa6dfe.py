import datetime

weekdays = {
		'Monday': 0,
		'Tuesday': 1,
		'Wednesday': 2,
		'Thursday': 3,
		'Friday': 4,
		'Saturday': 5,
		'Sunday': 6,
	}

teenths = range(13,20)

def meetup_day(year, month, a, b):

	weekday = weekdays[a]

	if b == 'last':

		start_date = datetime.date(year, month + 1, 1) - datetime.timedelta(1)

		while start_date.weekday() != weekday:
			start_date -= datetime.timedelta(1)

	else:
		start_date = datetime.date(year, month, 1)

		if b == 'teenth':
			while ((start_date.weekday() != weekday) or (start_date.day not in teenths)):
				start_date += datetime.timedelta(1)

		elif b in ['1st', '2nd', '3rd', '4th']:
			c = int(b[0])

			start_date -= datetime.timedelta(1)

			while c > 0:
				start_date += datetime.timedelta(1)
				if start_date.weekday() == weekday:
					c -= 1

		else:
			start_date = None

	return start_date
