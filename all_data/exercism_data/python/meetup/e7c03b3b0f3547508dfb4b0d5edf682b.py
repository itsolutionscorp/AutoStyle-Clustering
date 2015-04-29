from datetime import timedelta
from datetime import date

dayMap = {'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3, 'Friday':4, 'Saturday':5, 'Sunday':6}
modifierMap = {'1st':0, '2nd':1, '3rd':2, '4th':3}

def meetup_day(year, month, dayString, modifier):
	"""Find first meeting day in specified year and month.
	"""

	# level set. find first instance of the day we want in specified month
	workingDate = findFirstDayInMonth(year, month, dayMap[dayString])

	# Now populate list with all days in month, including
	# the first one we just identified
	daysInMonth = []
	while workingDate.month == month:
		daysInMonth.append(workingDate)
		workingDate += timedelta(7)

	# Now select which day from the list we want
	if modifier == 'teenth':
		for day in daysInMonth:
			if isTeenth(day.day):
				return day
	elif modifier == 'last':
		return daysInMonth[-1]
	else:
		return daysInMonth[modifierMap[modifier]]

def isTeenth(day):
	if day > 12 and day < 20:
		return True
	return False

def findFirstDayInMonth(year, month, day):
	workingDate = date(year, month, 1)
	weekday = workingDate.weekday()

	# Find closest previous Monday to the first of the month,
	# and then add back the offset for the day we want. If the
	# result is still in the previous month, add another week
	workingDate = workingDate - timedelta(weekday) + timedelta(day)
	if workingDate.month < month:
		workingDate = workingDate + timedelta(7)

	return workingDate
