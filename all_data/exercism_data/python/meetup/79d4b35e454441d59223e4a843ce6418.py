import datetime
import calendar

def meetup_day(year, month, day, occurence):
	week_multiplier = 0
	dayofweekconverter = {
		'Monday': 0,
		'Tuesday': 1,
		'Wednesday': 2,
		'Thursday': 3,
		'Friday': 4,
		'Saturday': 5,
		'Sunday': 6,
	}
		
	if occurence == "last":
		# counts from seven final days of the month
		print "ZS"
		week_multiplier = 0
		init_date = datetime.date( year, month, calendar.monthrange(year, month)[1] - 6)
	elif occurence == "teenth":
		# counts from 13th day of the month
		week_multiplier = 0
		init_date = datetime.date( year, month, 13 )
	else:
		init_date = datetime.date( year, month, 1 )
		if occurence == '1st':
			week_multiplier = 0
		elif occurence == '2nd':
			week_multiplier = 1
		elif occurence == '3rd':
			week_multiplier = 2
			print week_multiplier
		elif occurence == '4th':			
			week_multiplier = 3
		else:
			print "fuck"

	if dayofweekconverter[day] - init_date.weekday() < 0:
		week_multiplier += 1
		
	new_date = init_date + datetime.timedelta( 7 * week_multiplier + ( dayofweekconverter[day] - init_date.weekday() ) )
	return new_date
	
