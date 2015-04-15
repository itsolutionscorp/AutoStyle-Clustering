import datetime, calendar

def meetup_day(year, month, day, nth):
	"""
	Takes the description of a date in a year, month, 
	name of the day (string) and the nth time of that day in the month (1st-4th, teenth).
	Returns a datetime.date object of that date
	"""
    #skip immediately to the teenth values
	if nth == 'teenth':
		weekday = 13
	else:
		weekday = 1
	
	#loop till the weekday matches the dayname
	dt = datetime.date(year = year, month = month, day = weekday)
	day_arr = calendar.day_name
	while day_arr[dt.weekday()] != day:
		dt += datetime.timedelta(days=1)
	
	#skip ahead to appropriate week (if not a teenth value
	nths = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, 'last': 4}
	if nth != 'teenth':
		dt += datetime.timedelta(days= 7 * nths[nth])
	
	return dt
