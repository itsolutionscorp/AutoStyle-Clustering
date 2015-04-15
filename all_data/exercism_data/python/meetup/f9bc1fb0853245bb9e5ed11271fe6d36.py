import calendar
import datetime

days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
weeks = ['1st', '2nd', '3rd', '4th']
def meetup_day(year, month, day_s, clue):
	#gets first day and length for month
	test = calendar.monthrange(year, month)
	first_weekday = test[0]
	days_in_month = test[1]

	#returns (date, day of week) for each week
	cal = calendar.Calendar(firstweekday=first_weekday)
	list_by_week = cal.monthdays2calendar(year, month)
	

	if clue == "teenth":
		test_range = [x for x in cal.itermonthdays2(year, month)]
		test_range = test_range[12:19]
		print (test_range)
	
	if clue == "last":
		test_range = list_by_week[-1]
	
	if clue in weeks:
		test_range = list_by_week[(weeks.index(clue))] 

	for x in test_range:
		if x[1] == days.index(day_s):
			return datetime.date(year, month, x[0])



	
