import calendar

def meetup_day(year, month, daystr, modifier):
	cal = calendar.Calendar()
	occurences = 0
	final_day = None

	for day in cal.itermonthdates(year, month):
		if day.strftime('%A') == daystr:
			if day.month == month:
				occurences += 1
				final_day = day
				if modifier[0].isdigit():
					if int(modifier[0]) == occurences:
						return day
				elif modifier != 'last':
					if day.day in range(13,20):
						return day

	if modifier == 'last':
		return final_day
	else:
		raise IndexError("No {0} {1} in {3}/{4}".format(modifier, daystr, month, year))
