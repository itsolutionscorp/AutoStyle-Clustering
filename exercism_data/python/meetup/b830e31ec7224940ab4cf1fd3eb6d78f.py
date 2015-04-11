# function that determines the exact date given its year, month, day of week
# and location in the month. Options for location include 1st, 2nd, 3rd,
# 4th, last, and teenth.

import datetime
import calendar



def meetup_day(year, month, weekday, teenth):

	# create a dictionary to convert weekdays into calendar format

	y = ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')
	weekdayDict = {}
	for i, v in enumerate(y):
		weekdayDict[v] = i	

	# get the possible days of a given month and year that fall on weekday

	dayList = []
	cal = calendar.Calendar()
	
	for i in cal.itermonthdays2(year, month):
		x, y = i
		if y == weekdayDict[weekday] and x != 0: dayList.append(x)		

	# determine which of these days meets the 'teenth' criteria

	if teenth == '1st':
		day = dayList[0]
	elif teenth == '2nd':
		day = dayList[1]
	elif teenth == '3rd':
		day = dayList[2]
	elif teenth == '4th':
		day = dayList[3]
	elif teenth == 'last':
		day = dayList[-1]
	elif teenth == 'teenth':
		day = next(i for i in dayList if i >=13 and i <= 19)
	

	return datetime.date(year, month, day)
