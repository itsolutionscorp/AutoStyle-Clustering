from calendar import monthrange
from datetime import date

daydictionary = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}

def meetup_day(year, month, daystring, whichone):
	(firstday,numberofdays) = monthrange(year, month)
	if whichone == 'last':
		lastday = (firstday + numberofdays - 1) % 7
		dayincrementback = (lastday - daydictionary[daystring]) % 7
		return date(year, month, numberofdays - dayincrementback)
	dayincrement = (daydictionary[daystring] - firstday) % 7
	if whichone == '1st':
		return date(year, month, 1 + dayincrement)
	if whichone == '2nd':
		return date(year, month, 8 + dayincrement)
	if whichone == '3rd':
		return date(year, month, 15 + dayincrement)
	if whichone == '4th':
		return date(year, month, 22 + dayincrement)
	if whichone == 'teenth':
		if dayincrement > 4:
			return date(year, month, 8 + dayincrement)
		else: 
			return date(year, month, 15 + dayincrement)
