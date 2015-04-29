import datetime
import calendar

def meetup_day(year, month, day, number):
	cal = calendar.Calendar()

	daytonum = {"monday": 0, "tuesday": 1,"wednesday": 2,"thursday": 3,"friday":4,"saturday":5,"sunday": 6}
	weekday_val = daytonum[day.lower()]
	dates = []
	teenths = []
	

	for d in cal.itermonthdays2(year, month): 
		if d[1] == weekday_val and d[0] > 0:
			dates.append(d[0])
			if d[0] >= 10 and d[0] < 20:
				teenths.append( d[0])
				


	if number == '1st':
		
		return datetime.date(year, month, dates[0] )
	elif number == '2nd':
		return datetime.date(year, month, dates[1])

	elif number == '3rd':
		
		return datetime.date(year, month, dates[2])

	elif number == '4th':
		return  datetime.date(year, month, dates[3])

	elif number == '5th':
		return  datetime.date(year, month, dates[4])

	elif number == 'last':
		return  datetime.date(year, month, dates[-1])

	elif number == 'teenth':
		return  datetime.date(year, month, teenths[0])
			

	
