import calendar

def meetup_day(year, month, daystr, modifier):
	days = {'Monday' : 0, 'Tuesday' : 1, 'Wednesday' : 2,
	        'Thursday' : 3, 'Friday' : 4, 'Saturday' : 5,
		'Sunday' : 6}
	teenths = [13, 14, 15, 16, 17, 18, 19]
	cal = calendar.Calendar() #Monday is day 0
	occurences = 0
	final_day = None

	for day in cal.itermonthdates(year, month):
		if day.weekday() == days[daystr]:
			if day.month == month:
				occurences += 1
				final_day = day
				if modifier[0].isdigit():
					if int(modifier[0]) == occurences:
						return day
				elif modifier != 'last':
					if day.day in teenths:
						return day

	if modifier == 'last':
		return final_day
	else:
		raise IndexError("No {0} {1} in {3}/{4}".format(modifier, daystr, month, year))
