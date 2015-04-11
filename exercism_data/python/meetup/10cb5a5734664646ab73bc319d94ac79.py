import datetime
from datetime import date
import calendar


def meetup_day(year,month,day,quater):
	""" takes a date, returns date-type its first, second, teenth, third, fourth , last day-date
	meetup_day(2013, 5, 'Monday', 'teenth') get the day (monday) of the 1st of 5th,
	then count +3 for days, +1 for Tuesday, then +3*7 for last etc.
	
	if 1st is monday, 0+1
	1st is a tuesday 2%1
	"""
	dict1={'Monday':0,'Tuesday':1,'Wednesday':2,'Thursday':3,'Friday':4,'Saturday':5,'Sunday':6}
	dict2={'1st':0,'2nd':7,'3rd':14,'4th':21,'last':'last','teenth':'teenth'}
	month_range=calendar.monthrange(year,month)
	
	
	if dict2[quater]=='last':
		day_count=month_range[1]
	elif dict2[quater]=='teenth':
		day_count=(dict1[day]-month_range[0])+15

	else:
		day_count=int(dict2[quater])+((dict1[day]-month_range[0])%7)+1

	return datetime.date(year, month, day_count)
