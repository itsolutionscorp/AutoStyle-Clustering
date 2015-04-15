import datetime
import calendar


def meetup_day(year, month, day_of_week, which):
	'''
	Find day of month matching day of the week and given criteria
	'''

	which_choices = {
			'1st': 0,
			'2nd': 7,
			'3rd': 14,
			'4th': 21,
			'last': -1,
			'teenth': -2
		}
	weekdays = {
			'Monday': 0,
			'Tuesday': 1,
			'Wednesday': 2,
			'Thursday': 3,
			'Friday': 4,
			'Saturday': 5,
			'Sunday': 6
		}
	start_date = datetime.date(year, month, 1)
	start_weekday, days_in_month = calendar.monthrange(year, month)
	day_wanted = weekdays[day_of_week]

	# Get difference between (first day of month) weekday and required weekday
	if start_weekday > day_wanted:
		start_date += datetime.timedelta(7 - start_weekday + day_wanted)
	else:
		start_date += datetime.timedelta(day_wanted - start_weekday)
	choice = which_choices[which]

	# Special case of 'teenth' and 'last'
	if choice in (-1, -2):
		if choice == -2:
			if start_date.day >= 6:
				choice = 7
			else:
				choice = 14
		else:
			# If end of month ('last'), timedelta(days) will be negative
			start_date = datetime.date(year, month, days_in_month)
			end_weekday = start_date.weekday()
			if day_wanted > end_weekday:
				choice = - (7 - (day_wanted - end_weekday))
			else:
				choice = - (end_weekday - day_wanted)
	start_date += datetime.timedelta(choice)
	return start_date
