import calendar
import datetime

# days of the week begin on Monday, where Monday = 0

# calculate the date of a meetup
def meetup_day(year, month, weekday_str, specifier):
	weekday_str_to_weekday = {'Monday':0, 'Tuesday':1, 'Wednesday':2,
								'Thursday':3, 'Friday':4, 'Saturday':5, 'Sunday':6}
	specifier_to_offset = {'1st':0,'2nd':1, '3rd':2, '4th':3}

	[weekday_first,month_length] = calendar.monthrange(year,month)
	weekday = weekday_str_to_weekday[weekday_str]
	
	# Calculate the day on which the given condition is meet
	if (specifier=='teenth'):
		# Find the weekday of the 13th, and use this to find the number of days
		# (after the 13th) until the next occurrence of the desired weekday,
		# plus 13 days.
		day = 13 + (weekday - weekday_first + 2)%7
	elif (specifier=='last'):
		# Find the weekday of the last day of the month,
		# then determine the number of days since the desired weekday.
		# Subtract this value from the number of days in the month.
		day = month_length - (-weekday + weekday_first + month_length - 1)%7
	else:
		# Find days until the first occurrence of the desired weekday,
		# then add this to some number of weeks.
		day = 1 + (weekday-weekday_first) % 7
		day += specifier_to_offset[specifier] * 7

	return datetime.date(year,month,day)
	
