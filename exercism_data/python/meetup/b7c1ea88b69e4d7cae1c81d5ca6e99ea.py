import calendar, datetime

def meetup_day(year, month, weekday, which):
	month_matrix = calendar.monthcalendar(year, month)
	weekday_num = {
		'Monday':		0,
		'Tuesday':		1,
		'Wednesday':	2,
		'Thursday':		3,
		'Friday':		4,
		'Saturday':		5,
		'Sunday':		6
	}[weekday];

	if which == 'last':
		day = month_matrix[-1][weekday_num]
		if not day:
			day = month_matrix[-2][weekday_num]
		return datetime.date(year, month, day)

	elif which == 'teenth':
		for week in month_matrix:
			if is_teenth(week[weekday_num]):
				return datetime.date(year, month, week[weekday_num])
		raise Exception('This can not happen')

	else: # 1st, 2nd, ...
		week_nth = int(which.rstrip('stndrdth'))
		if month_matrix[0][weekday_num] <= 0:
			month_matrix = month_matrix[1:]
		if len(month_matrix) < week_nth:
			raise Exception("There's no {0} {1} on {2}/{3}".format(which, weekday, month, year))
		return datetime.date(year, month, month_matrix[week_nth-1][weekday_num])

def is_teenth(day_num):
	return day_num >= 13 and day_num <= 19
