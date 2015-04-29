from datetime import date

weekday_code = {0: 'Monday', \
		1: 'Tuesday', \
		2: 'Wednesday', \
		3: 'Thursday', \
		4: 'Friday', \
		5: 'Saturday', \
		6: 'Sunday'}

def is_leap_year(year):
	if year % 400 == 0:
		return True
	elif year % 100 == 0:
		return False
	elif year % 4 == 0:
		return True
	else:
		return False

def meetup_day(year, month, day_week, pos_month):
	# defines the range of possible days based on
	# the position of the day in the month
	if pos_month == 'teenth':
		range_days = range(13, 20)
	elif pos_month == '1st':
		range_days = range(1, 8)
	elif pos_month == '2nd':
		range_days = range(8, 15)
	elif pos_month == '3rd':
		range_days = range(15, 22)
	elif pos_month == '4th':
		range_days = range(22, 29)
	elif pos_month == 'last':
		# defines last week of a month based on
		# which month it is
		if month in [1, 3, 5, 7, 8, 10, 12]:
			first_day = 25
			last_day = 31
		elif month in [4, 6, 9, 11]:
			first_day = 24
			last_day = 30
		else:
			if is_leap_year(year):
				first_day = 23
				last_day = 29
			else:
				first_day = 22
				last_day = 28
		range_days = range(last_day, first_day-1, -1)
	# searches for a week day equal to the one given
	# in the range previously determined
	for day in range_days:
		if weekday_code[date(year, month, day).weekday()] == day_week:
			day_month = day	
	return date(year, month, day_month)
