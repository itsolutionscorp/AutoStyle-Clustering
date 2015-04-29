from datetime import date
from calendar import monthrange
weekday_lookup = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
ord_lookup = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, 'last': 4, 'teenth': 0}
def meetup_day(year, month, weekday, day_ord):
	target_weekday = weekday_lookup[weekday]
	if day_ord == 'teenth':
		start_weekday = date(year, month, 13).weekday()
		month_start = 13
		month_length = 19
	else:
		start_weekday, month_length = monthrange(year, month)
		month_start = 1
	day = month_start + (target_weekday - start_weekday)
	if day < month_start:
		day += 7
	day += 7 * ord_lookup[day_ord]
	if day > month_length:
		day -= 7
	return date(year, month, day)
