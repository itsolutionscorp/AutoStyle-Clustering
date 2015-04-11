import calendar
import datetime

def meetup_day(year, month, weekday, ordinal):
	start_day = {
		'teenth': 13,
		'1st': 1, '2nd': 8, '3rd': 15, '4th': 22,
		'last': calendar.monthrange(year, month)[1] - 6
	}

	target = calendar.day_name[:].index(weekday)
	start = datetime.date(year, month, start_day[ordinal]).weekday()
	diff = (target - start) % 7

	return datetime.date(year, month, start_day[ordinal] + diff)
