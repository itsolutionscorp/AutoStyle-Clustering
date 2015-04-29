from datetime import date
from calendar import monthrange

def meetup_day(year, month, weekday, week):
	for i in range(1, monthrange(year, month)[1]+1):
		day = date(year, month, i)
		if day.strftime('%A') == weekday:
			if week == '1st':
				if -7 < i - 7 <= 0:
					return day
			elif week == '2nd':
				if -7 < i - 14 <= 0:
					return day
			elif week == '3rd':
				if -7 < i - 21 <= 0:
					return day
			elif week == '4th':
				if -7 < i - 28 <= 0:
					return day
			elif week == 'teenth':
				if -7 < i - 19 <= 0:
					return day
			elif week == 'last':
				if -7 < i - monthrange(year, month)[1] <= 0:
					return day
