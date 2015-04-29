from datetime import date
import calendar

def meetup_day(year, month, newDay, suffix):


	if suffix == 'last':
		return date(year, month, calendar.monthrange(year, month)[1])


	elif suffix == '1st':
		
		return date_iterator(year, month, 1, newDay)

	elif suffix == '2nd':
		
		return date_iterator(year, month, 8, newDay)

	elif suffix == 'teenth':

		return date_iterator(year, month, 13, newDay)

	elif suffix == '3rd':

		return date_iterator(year, month, 15, newDay)
	
	elif suffix == '4th':

		return date_iterator(year, month, 22, newDay)



def date_iterator(year, month, day, newDay):

	daysDict = {'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3, 'Friday':4, 'Saturday':5, 'Sunday':6}

	while daysDict[newDay]!= calendar.weekday(year, month, day):
			day = day + 1
		
	return date(year, month, day)
