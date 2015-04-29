from datetime import date
from calendar import monthrange

def meetup_day(year, month, day_of_the_week, which_day):
	"""Calculate the date of meetups."""

	weekday = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
	first_day = monthrange(year, month)[0]

	if which_day == '1st':
		actual_date = (weekday[day_of_the_week] - first_day) % 7 + 1
	elif which_day == '2nd':
		actual_date = (weekday[day_of_the_week] - first_day) % 7 + 1 + 7
	elif which_day == '3rd':
		actual_date = (weekday[day_of_the_week] - first_day) % 7 + 1 + 2 * 7
	elif which_day == '4th':
		actual_date = (weekday[day_of_the_week] - first_day) % 7 + 1 + 3 * 7
	elif which_day == 'last':
		first_of_last = date(year, month, monthrange(2013, 10)[1] - 5).weekday()
		actual_date = monthrange(2013, 10)[1] - 5 + ((weekday[day_of_the_week] - first_of_last) % 7)
	elif which_day == 'teenth':
		first_of_teenths = date(year, month, 13).weekday()
		actual_date = 13 + ((weekday[day_of_the_week] - first_of_teenths) % 7)

	return date(year, month, actual_date)
