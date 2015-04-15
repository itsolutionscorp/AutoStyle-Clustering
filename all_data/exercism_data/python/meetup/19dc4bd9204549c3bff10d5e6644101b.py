import datetime, calendar
def meetup_day(year, month, day_name, occurence):
	year_int = int(year)
	month_int = int(month)
	# day_int is set to an int between 0 to 6
	day_int = list(calendar.day_name).index(day_name)
	# makes a list of day_ints in the month of the year passed, ignoring the 0
	relevant_mdays = [i for i in zip(*calendar.monthcalendar(year, month))[day_int] if i != 0]
	if occurence == 'teenth':
		# next goes to the next day in the relevant_mdays list, adding teenth days to mday
		mday = next((i for i in relevant_mdays if i in range(13, 20)), None)
	else:
		if occurence == 'last':
			# sets occurence to the last item on the relevant_mdays list
			occurence = -1
		else:
			# occurence is set to 1st, 2nd, 3rd, or 4th in the list
			occurence = int(occurence[0]) - 1
		#mday is picked from the list
		mday = relevant_mdays[occurence]
	return datetime.date(year_int, month_int, mday)
