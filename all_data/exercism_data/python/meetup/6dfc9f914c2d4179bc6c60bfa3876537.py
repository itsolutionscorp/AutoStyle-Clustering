from datetime import date
from calendar import monthrange

weekday_lookup = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}

def meetup_day(year, month, weekday, day_ord):
	ord_lookup = {'1st': 1, '2nd': 8, '3rd': 15, '4th': 22, 'teenth': 13, 'last': monthrange(year, month)[1] - 6}

	target_weekday = weekday_lookup[weekday]

	start_weekday = date(year, month, ord_lookup[day_ord]).weekday()

	diff = target_weekday - start_weekday
	if diff < 0:
		diff += 7

	return date(year, month, ord_lookup[day_ord] + diff)
