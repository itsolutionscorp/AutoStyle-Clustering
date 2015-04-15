import datetime

def meetup_day(year, month, day, condition):
	weekdays = {'Monday': 1, 'Tuesday': 2, 'Wednesday': 3, 'Thursday': 4, 'Friday': 5, 'Saturday': 6, 'Sunday': 7}
	weekday = weekdays[day]
	d = datetime.date(year, month, 1)
	days = []
	while d.month == month:
		if d.isoweekday() == weekday:
			days.append(d.day)
		d += datetime.timedelta(1)
	try:
		c = int(condition[0])
		return datetime.date(year, month, days[c-1])
	except ValueError:
		if condition == 'teenth':
			for i in days:
				if 13 <= i <= 19:
					return datetime.date(year, month, i)
		else:
			return datetime.date(year, month, days[-1])
