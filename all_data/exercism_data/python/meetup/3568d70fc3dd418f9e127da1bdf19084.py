import calendar
import datetime

def meetup_day(year, month, weekday, whatth):
	calendar.setfirstweekday(calendar.SUNDAY)
	cal = calendar.Calendar(6)

	#Check for valid weekday entry
	if weekday == 'Sunday':
		wkday = calendar.SUNDAY
	elif weekday == 'Monday':
		wkday = calendar.MONDAY
	elif weekday == 'Tuesday':
		wkday = calendar.TUESDAY
	elif weekday == 'Wednesday':
		wkday = calendar.WEDNESDAY
	elif weekday == 'Thursday':
		wkday = calendar.THURSDAY
	elif weekday == 'Friday':
		wkday = calendar.FRIDAY
	elif weekday == 'Saturday':
		wkday = calendar.SATURDAY
	else:	raise ValueError("Weekday must be a day of the week 'Sunday' through 'Saturday'")

	#Check for valid whatth entry
	if whatth == "1st":
		counter = 1
	elif whatth == "2nd":
		counter = 2
	elif whatth == "3rd":
		counter = 3
	elif whatth == "4th":
		counter = 4
	elif whatth == "5th": 
		counter = 5
	elif whatth == "last":
		counter = 9
	elif whatth == "teenth":
		counter = 10
	else:
		raise ValueError("Invalid week number")

	#Solve "teenth" case
	if counter == 10:
		for day in cal.itermonthdates(year, month):
			if day.month == month and day.weekday() == wkday and 13 <= day.day <= 19:
				return day

	#Solve "last" case
	if counter == 9:
		for day in cal.itermonthdates(year, month):
			if day.month == month and day.weekday() == wkday:
				lastday = day
		return lastday

	if 1 <= counter <= 5:
		for day in cal.itermonthdates(year, month):
			if day.month == month and day.weekday() == wkday:
				counter = counter - 1
				if counter == 0:
					return day
		raise MeetupDayException
