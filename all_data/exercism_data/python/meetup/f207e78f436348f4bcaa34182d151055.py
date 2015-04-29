from datetime import date, timedelta
import calendar

week_days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday', ]

def meetup_day(year, month, day, ordinal):
	day_of_week_iso = week_days.index(day)
	
	if ordinal[0].isdigit():
		start = date(year, month, 1)
		off_set_for_day = (day_of_week_iso - start.weekday()) % 7
		ordinal_off_set = (int(ordinal[0]) - 1) * 7

	elif ordinal == "last":
		start = date(year, month, calendar.monthrange(year, month)[1])
		off_set_for_day = - ((day_of_week_iso - start.weekday()) % 7)
		ordinal_off_set = 0

	elif ordinal == "teenth":
		start = date(year, month, 10)
		off_set_for_day = (day_of_week_iso - start.weekday()) % 7
		ordinal_off_set = 0

	return start + timedelta(days=off_set_for_day + ordinal_off_set) 
