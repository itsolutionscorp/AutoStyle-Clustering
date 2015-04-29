def meetup_day(year, month, weekday, which): 
# this method takes a year, month, name of day of the week, and a description of a meetup date
# ("1st", "2nd", "3rd", "4th", "last", or "teenth"), and returns that date as datetime object

	import datetime
	import calendar
	
	# turns the day of the week into a digit (0-6, m-sun)
	weekday = weekday.lower()
	which = which.lower()
	
	week_days = {"monday": 0, "tuesday": 1, "wednesday": 2, 
	"thursday": 3, "friday": 4, "saturday": 5, "sunday": 6}
	
	for day in week_days:
		if (day == weekday):
			digit = week_days[day] 	
	#digit now represents the day of the week
	
	case = {"1st": 1, "2nd": 2, "3rd": 3, "4th": 4, "last": 5, "teenth": 6}
	
	#turn var which into an int representing the case
	for i in case: 
		if (which == i): 
			which = case[i]
		
	# this is the number of days in month		
	month_length = calendar.monthrange(year, month)[1]
	
	#satisfies all cases except "teenth"
	if (which != 6): 
		counter = 0
		for x in range(1, month_length + 1): 
			#finds each of the particular weekday, saves day it lands on as x
			if (calendar.weekday(year, month, x) == digit): 
				day = x
				counter = counter + 1
				
			# this will stop looping if you have reached the nth weekday
			if (counter == which): 
				break
		
	if (which == 6): 
		#satisfies "teenth"
		for x in range(13, 19): 
			if (calendar.weekday(year, month, x) == digit): 
				day = x 
	
	return datetime.date(year, month, day)
