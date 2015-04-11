import re
from datetime import date

def meetup_day(year,month,day,modifier):

	days_of_week = {}
	days_of_week['Monday'] = 0
	days_of_week['Tuesday'] = 1
	days_of_week['Wednesday'] = 2
	days_of_week['Thursday'] = 3
	days_of_week['Friday'] = 4
	days_of_week['Saturday'] = 5
	days_of_week['Sunday'] = 6

	re_numerals = re.compile('\d')

	#If modifier is 1st,2nd,3rd,4th
	if bool(re_numerals.search(modifier)):
		search_start = 1
		offset_modifier = (int(modifier[0])-1)*7

	#If in the teens
	elif modifier == 'teenth':
		search_start = 13
		offset_modifier = 0

	elif modifier == 'last':
		search_start = 28 #Largest date available in all months
		offset_modifier = 0

	
	start_day = date(year,month,search_start).weekday()
	wanted_day = days_of_week[day]
	offset = wanted_day-start_day
	#^get difference between start_day and wanted in terms of days_of_week

	if offset < 0:
		return date(year,month,offset+search_start+offset_modifier+7)
	else: 
		return date(year,month,offset+search_start+offset_modifier)
