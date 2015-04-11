from datetime import date
import calendar

def meetup_day(year, month, dow, whichweek):
	"""Calculates and returns the date of a meetup
	
	Keyword arguments:
	year -- interger representing a year
	month -- integer [1..12] representing a month
	dow -- name of the day of week 
	whickweek -- wich occurence of the day of week
	"""

	#Prepare dictionaries
	dayofweek = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
	weekdict = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, 'last': -1}
	# Instantiate calendar
	cal = calendar.Calendar()
	try:
		# Check the wird definition of occurence
		if whichweek == 'teenth':
			day = filter(lambda (x,y): x in range(13,20) and y==dayofweek[dow], cal.itermonthdays2(year, month))[0]
		# Check normal definiotions of occurence as in weekdict
		else:
			day = filter(lambda (x,y): x != 0 and y==dayofweek[dow], cal.itermonthdays2(year, month))[weekdict[whichweek]]
		return date(year, month, day[0])
	# Catch errors if arguments are of wrong type or value
	except TypeError:
		return None
	except ValueError:
		return None
	except KeyError:
		return None
	

# Standalone debugging
if __name__ == '__main__':
	print meetup_day(2012, 12, 'Tuesday', '1st')
	print meetup_day(2013, 5, 'Monday', 'teenth')
	print meetup_day(2013, 3, 'Sunday', 'teenth')
