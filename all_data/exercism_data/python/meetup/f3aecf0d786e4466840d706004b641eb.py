import datetime
from datetime import date
from datetime import timedelta

import re

days = {0:'Monday', 1:'Tuesday', 2:'Wednesday', 3:'Thursday', 4:'Friday', 5:'Saturday', 6:'Sunday'}


class MeetupDayException(Exception):
	pass

def checkInputs(year, month, day, position):
	try:
		cYear = int(year)
		cMonth = int(month)
	except ValueError:
		raise MeetupDayException("Wrong year/month inputs, please check the values entered")

	if not day in days.values():
		raise MeetupDayException("Wrong day input, please check the value entered")		

	posi = re.search('[0-9]|teenth|last', position)

	if not posi:
		raise MeetupDayException("Wrong position input, please check the value entered")
	else:
		return posi.group(0)


def meetup_day(year, month, day, position):	
	pos = checkInputs(year, month, day, position)

	if ( pos != None ):
		referenceDate = date(year, month, 1)
		count = 0
		referenceDay = date.weekday(referenceDate)
		while ( days[referenceDay].upper() != day.upper() ):
			referenceDate = referenceDate + timedelta(days=1)
			referenceDay = date.weekday(referenceDate)
			# Now we've reached the first day of the month corresponding to our input ('Monday', 'Tuesday', etc...)
		if ( pos == 'teenth' ):
			while ( referenceDate.day < 13 ):
				referenceDate = referenceDate + timedelta(days=7)
		else:
			count = 1
			if ( pos  == 'last' ):
				# For the 'last' position, it's easiest to begin from first day of next month
				# and go backwards from there
				referenceDate = date(year, month + 1, 1)
				while ( days[referenceDate.weekday()] != day ):
					referenceDate = referenceDate - timedelta(days=1)
				return referenceDate
			while ( count != int(pos) ):
				referenceDate = referenceDate + timedelta(days=7)
				if ( referenceDate.month != month ):
					raise MeetupDayException("This day does not exist!")
				count = count + 1
		return referenceDate
	else:
			raise MeetupDayException("Wrong day value")
