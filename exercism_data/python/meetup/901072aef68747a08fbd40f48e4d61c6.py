from datetime import date, timedelta

weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']

occurrenceRef = ['1st', '2nd', '3rd', '4th']

def meetup_day(year, month, dayOfTheWeek, occurrence):
	def isTeenth():
		nonlocal result
		referenceDate = date(year, month, 13)
		dayNumberTemp = weekdays.index(dayOfTheWeek) - referenceDate.weekday()
		if dayNumberTemp < 0:
			result = referenceDate + timedelta(days=(7 + dayNumberTemp))
		else:
			result = referenceDate + timedelta(days=(dayNumberTemp))

	def isLast():
		nonlocal result
		referenceDate = date(year, month + 1, 1) - timedelta(days=1)
		dayNumberTemp = weekdays.index(dayOfTheWeek) - referenceDate.weekday()
		if dayNumberTemp > 0:
			result = referenceDate + timedelta(days=(dayNumberTemp - 7))
		else:
			result = referenceDate + timedelta(days=(dayNumberTemp))
		
	result = date(year, month, 1)
	if occurrence is 'teenth':
		isTeenth()
	elif occurrence is 'last':
		isLast()
	else:
		referenceDate = date(year, month, 1)
		dayNumberTemp = weekdays.index(dayOfTheWeek) - referenceDate.weekday()
		if dayNumberTemp < 0:
			result = referenceDate + timedelta(days=(7 + dayNumberTemp)) + timedelta(weeks=occurrenceRef.index(occurrence))
		else:
			result = referenceDate + timedelta(days=(dayNumberTemp)) + timedelta(weeks=occurrenceRef.index(occurrence))
	return result
