from datetime import *
from calendar import monthrange

def meetup_day(year, month, day=0, cond=None):
	
	conds = ['1st', '2nd', '3rd', '4th', 'teenth', 'last']
	
	if not cond in conds:
		return None
	
	d = date(year, month, 01) # Assing date to variable

	if day == 'Monday':
		x = 0
	elif day == 'Tuesday':
		x = 1
	elif day == 'Wednesday':
		x = 2
	elif day == 'Thursday':
		x = 3
	elif day == 'Friday':
		x = 4
	elif day == 'Saturday':
		x = 5
	elif day == 'Sunday':
		x = 6
	
	daysfound = 0

	while True:

		if d.weekday() == x: # weekday is equal to the weekday to search for

			daysfound += 1 # count days found

			if cond == 'teenth':				
				if d.day >= 13 and d.day <=19: # if  12 < day < 20
					return d
				else:
					d = d + timedelta(days=1)
			elif cond == '1st':
				if daysfound == 1: 
					return d
				else:
					d = d + timedelta(days=1)
			elif cond == '2nd':
				if daysfound == 2:
					return d
				else:
					d = d + timedelta(days=1)
			elif cond == '3rd':
				if daysfound == 3:
					return d
				else:
					d = d + timedelta(days=1)
			elif cond == '4th':
				if daysfound == 4:
					return d
				else:
					d = d + timedelta(days=1)
			elif cond == 'last':
				if monthrange(d.year,d.month)[1] - d.day < 7: # if it's the last day of the month
					return d
				else:
					d = d + timedelta(days=1)
		else:
			d = d + timedelta(days=1)
