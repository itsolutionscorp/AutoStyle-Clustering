from datetime import date

_weekdays=['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday']
_occurrence_interpretation={'1st':0, '2nd':7, '3rd':14, '4th':21, '5th':28, 'teenth':12}

# calculates the last occurrence of weekday before the given year and month
# year and month must be of type int with 1 <= month <= 12. weekday must be of type str and will only recognize specific values.
def _meetup_day_before(year, month, weekday):
	basedate=date(year,month,1).toordinal()-1
	seeking=_weekdays.index(weekday)
	for i in range(7):
		considering=date.fromordinal(basedate-i)
		if considering.weekday()==seeking:
			return considering
	return None

# calculates the appropriate meetup date
# year and month must be of type int with 1 <= month <= 12. weekday and occurence must be of type str, and will only recognize specific values.
# note: the 5th occurrence of a weekday within a month may occur in the following month
def meetup_day(year, month, weekday, occurrence):
	if occurrence.lower()=='last':
		return _meetup_day_before(year+1 if month==12 else year, 1 if month==12 else month+1, weekday)
	basedate=date(year,month,1+_occurrence_interpretation.get(occurrence.lower(),0)).toordinal()
	seeking=_weekdays.index(weekday)
	for i in range(7):
		considering=date.fromordinal(basedate+i)
		if considering.weekday()==seeking:
			return considering
	return None
