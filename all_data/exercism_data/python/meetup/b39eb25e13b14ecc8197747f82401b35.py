from datetime import *

DAYS = {
	'Monday'    : 0,
	'Tuesday'   : 1,
	'Wednesday' : 2,
	'Thursday'  : 3,
	'Friday'    : 4,
	'Saturday'  : 5,
	'Sunday'    : 6 
}

ORDINALS = {
	'1st'    : 0,
	'2nd'    : 1,
	'teenth' : 1,  # teenth is 2nd by default but could be 3rd
	'3rd'    : 2,
	'4th'    : 3,
	'last'   : 3   # last is 4th but could be 5th
}

def meetup_day(year, month, day_of_week, constraint):
	meetup_date = date(year, month, 1)
	while meetup_date.weekday() != DAYS[day_of_week]:
		meetup_date += timedelta(days=1)
	
	if constraint in ORDINALS:
		meetup_date += timedelta(weeks=ORDINALS[constraint])

	if constraint == 'teenth' and meetup_date.day < 13:
		meetup_date += timedelta(weeks=1)

	
	if constraint == "last":
		last = meetup_date + timedelta(weeks=1)
		if last.month == month:
			meetup_date = last
	
	return meetup_date
