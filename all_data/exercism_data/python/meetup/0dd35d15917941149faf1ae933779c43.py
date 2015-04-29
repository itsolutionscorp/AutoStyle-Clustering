import calendar

def meetup_day(year, month, weekday, meetup):
	days = calendar.Calendar().itermonthdates(year, month)
	if meetup == 'teenth':
		return get_day_in_range(days, weekday, 13, 19)
	elif meetup == '1st':
		return get_day_in_range(days, weekday, 1, 7)
	elif meetup == '2nd':
		return get_day_in_range(days, weekday, 8, 14)
	elif meetup == '3rd':
		return get_day_in_range(days, weekday, 15, 21)
	elif meetup == '4th':
		return get_day_in_range(days, weekday, 22, 28)
	elif meetup == 'last':
		returnday = None
		for day in days:
			if day.strftime("%A") == weekday:
				if not returnday:
					returnday = day
				elif day.day > returnday.day:
					returnday = day
		return returnday
	else:
		pass # Define default behavior here.

def get_day_in_range(days, weekday, min, max):
	for day in days:
		if day.strftime("%A") == weekday and day.day in range(min, max+1):
			return day
