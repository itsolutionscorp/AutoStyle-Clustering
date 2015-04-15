from datetime import date, timedelta
from calendar import monthrange
import re

def meetup_day(year, month, dayOfWeek, order):

	daysOfWeek = {
		'Monday': 0,
		'Tuesday': 1,
		'Wednesday': 2,
		'Thursday': 3,
		'Friday': 4,
		'Saturday': 5,
		'Sunday': 6
		}
	
	(firstWeekDay, daysInMonth) = monthrange(year, month)

	if daysOfWeek[dayOfWeek] >= firstWeekDay:
		firstDay = abs(daysOfWeek[dayOfWeek] - firstWeekDay) + 1
	else:
		firstDay = 7 - abs(daysOfWeek[dayOfWeek] - firstWeekDay) + 1
	
	m = re.match('^(\d)(st|nd|rd|th)$', order)
	if m is not None:
		day = firstDay + (int(m.group(1)) - 1) * 7
	elif order == 'last':
		day = firstDay + 28 if firstDay + 28 <= daysInMonth else firstDay + 21
	elif order == 'teenth':
		day = firstDay + 14 if 1 <= firstDay <= 5 else firstDay + 7;
	else:
		raise ValueError()

	return date(year, month, day)
