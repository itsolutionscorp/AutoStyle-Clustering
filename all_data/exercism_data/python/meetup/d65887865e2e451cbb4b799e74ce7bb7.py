from datetime import date
from calendar import monthrange

def meetup_day(year, month, weekday, week):
	suffix = 0
	try:
		suffix = int(week[0])
	except ValueError:
		pass
	for i in range(1, monthrange(year, month)[1]+1):
		day = date(year, month, i)
		if day.strftime('%A') == weekday:
			if suffix:
				if -7 < i - (suffix * 7) <= 0:
					return day
			elif week == 'teenth':
				if -7 < i - 19 <= 0:
					return day
			elif week == 'last':
				if -7 < i - monthrange(year, month)[1] <= 0:
					return day
