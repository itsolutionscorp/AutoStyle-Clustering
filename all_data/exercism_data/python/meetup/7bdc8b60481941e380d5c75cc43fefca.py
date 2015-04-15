from datetime import date 
import calendar

def meetup_day(yr, mnth, dayofweek, dayloc):
	dayofweekref = {'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3,
					'Friday':4, 'Saturday':5, 'Sunday':6}
	cal = calendar.monthcalendar(yr, mnth)
	meetdate = date

	if dayloc == 'teenth':
		for i in range(len(cal)):
			if 12 < cal[i][dayofweekref[dayofweek]] < 20:
				meetdate = date(yr, mnth, cal[i][dayofweekref[dayofweek]])
	
	elif dayloc.startswith(('1', '2', '3', '4')):
		target = int(dayloc[0])
		count = 0
		for i in range(len(cal)):
			if cal[i][dayofweekref[dayofweek]] != 0:
				count += 1
				if count == target:
					meetdate = date(yr, mnth, cal[i][dayofweekref[dayofweek]])
	
	elif dayloc == 'last':
		for i in range(len(cal)):
			if cal[i][dayofweekref[dayofweek]] != 0:
				meetdate = date(yr, mnth, cal[i][dayofweekref[dayofweek]])

	return meetdate
