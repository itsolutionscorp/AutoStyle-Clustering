from datetime import date
from calendar import monthrange

# Convert days(and abbrevations) to numbers
Days = {
	'MONDAY':0, 'MON':0,
	'TUESDAY':1, 'TUE':1, 'TUES':1,
	'WEDNESDAY':2, 'WED':2,
	'THURSDAY':3, 'THU':3, 'THUR':3,
	'FRIDAY':4, 'FRI':4,
	'SATURDAY':5, 'SAT':5,
	'SUNDAY':6, 'SUN':6,
}
# Convert 'which' string to number. 2 possibilities for the 'teenth', which is why they are all arrays
Which = {
	'1ST':[0], 'FIRST':[0],
	'2ND':[1], 'SECOND':[1],
	'3RD':[2], 'THIRD':[2],
	'4TH':[3], 'FOURTH':[3],
	'LAST':[-1],
	'TEENTH':[1,2],
}

# match and newday calculation portion of logic taken from http://code.activestate.com/recipes/425607/
def meetup_day(year, month, day, which):
	beg, days = monthrange(year, month)
	for whichL in Which[which.upper()]:
		match = (Days[day.upper()] - beg) % 7 + 1
		newday = range(match, days+1, 7)[whichL]
		if which.upper() == 'TEENTH':
			# Break out of while loop if we're in the 'teenth' of the month
			if newday > 12 and newday < 20:
				break
		
	return date(year, month, newday)
