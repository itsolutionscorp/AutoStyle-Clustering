from datetime import date, timedelta
from calendar import monthrange

weekday_translation = {
	'Monday': 0,
	'Tuesday': 1,
	'Wednesday': 2,
	'Thursday': 3,
	'Friday': 4,
	'Saturday': 5,
	'Sunday': 6
}

iteration_translation = {
	'1st': 0,
	'2nd': 1,
	'3rd': 2,
	'4th': 3
}

def meetup_day(year, month, day_of_week, iteration):

	day_of_week = weekday_translation[day_of_week]
	(initial_weekday, days_in_month) = monthrange(year, month)

	if iteration == 'last':
		normalized = date(year, month, days_in_month)
		delta = (normalized.weekday() - day_of_week) % 7
	
	elif iteration == 'teenth':
		normalized = date(year, month, 13)
		delta = (day_of_week - initial_weekday + 2) % 7
	
	else:
		normalized = date(year, month, 1)
		delta = (day_of_week - initial_weekday) % 7
		
		iteration = iteration_translation[iteration]
		delta = 7 * iteration + delta

	return normalized + timedelta(days = delta)
