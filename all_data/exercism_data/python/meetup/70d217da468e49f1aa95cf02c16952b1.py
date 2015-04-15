from datetime import date
import calendar

def meetup_day(year, month, dayofweek, whichday):
	
	dayofweekforthe1st = calendar.monthrange(year,month)[0]
	daysinmonth = calendar.monthrange(year,month)[1]
	if whichday == "teenth":
		day = translateDayOfWeek(dayofweek) - dayofweekforthe1st + 1
		while day < 13:
			day += 7		
	else:
		multiplier = getMultiplier(whichday,dayofweek,dayofweekforthe1st,daysinmonth)
		if translateDayOfWeek(dayofweek) < dayofweekforthe1st:
			day = multiplier * 7 - translateDayOfWeek(dayofweek) + 1
		else:
			day = (multiplier -1 ) * 7 + translateDayOfWeek(dayofweek) - dayofweekforthe1st + 1
	return date(year,month,day)

def getMultiplier(whichday,dayofweek,dayofweekforthe1st,daysinmonth):
	if whichday == "last":
		mult = int(daysinmonth/7) 
		if dayofweekforthe1st < dayofweek:
			mult += 1
		return mult

	elif whichday == "1st":
		return 1
	elif whichday == "2nd":
		return 2
	elif whichday == "3rd":
		return 3
	elif whichday == "4th":
		return 4
	elif whichday == "5th":
		return 5

	return 1 


def translateDayOfWeek(dayofweek):

	return {
		"Monday" : 0,
		"Tuesday" : 1,
		"Wednesday" : 2,
		"Thursday" : 3,
		"Friday" : 4,
		"Saturday" : 5,
		"Sunday" : 6,
		}[dayofweek]
