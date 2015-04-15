import calendar
from datetime import date


def meetup_day(year, month, day, interval):

	c = calendar.monthcalendar(year, month)
	first_week = c[0]
	second_week = c[1]
	third_week = c[2]
	fourth_week = c[3]
	fifth_week = c[4]

	week_dict = dict(zip(calendar.weekheader(9).split(), range(7)))

	week_day_numeric = week_dict[day]

	def is_a_teenth(date):
		return date >= 13 and date <= 19

	if interval == 'teenth':		
		if is_a_teenth(second_week[week_day_numeric]):
			therightday = second_week[week_day_numeric]
		elif is_a_teenth(third_week[week_day_numeric]):
			therightday = third_week[week_day_numeric]
		else:
			therightday = fourth_week[week_day_numeric]

	elif interval == 'last':
		if fifth_week[week_day_numeric] != 0:
			therightday = fifth_week[week_day_numeric]
		else:
			therightday = fourth_week[week_day_numeric]
	
	elif interval[0].isdigit():
		nth_week = c[int(interval[0]) - 1]

		# Check to see if day exists in first week
		if first_week[week_day_numeric] == 0:
			next_week = c[int(interval[0])]
			therightday = next_week[week_day_numeric]
		else:
			therightday = nth_week[week_day_numeric]
	
	else:
		print "FUCK, I shouldn't be here %s =" % (interval)

	return date(year, month, therightday)
