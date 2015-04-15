from datetime import date, timedelta
def meetup_day(year, month, day_of_week, day_range):
	
	#determine which day of the week it is
	this_day = 0
	if day_of_week == "Tuesday":
		this_day = 1
	elif day_of_week == "Wednesday":
		this_day = 2
	elif day_of_week == "Thursday":
		this_day = 3
	elif day_of_week == "Friday":
		this_day = 4
	elif day_of_week == "Saturday":
		this_day = 5
	elif day_of_week == "Sunday":
		this_day = 6
	
	print "Day is ", this_day
	
	#find the date of that first occurence
	meetup_date = date(year,month,1)
	print "First day is ", meetup_date.weekday()
	if meetup_date.weekday() > this_day:
		meetup_date = meetup_date + timedelta(days=7-meetup_date.weekday()+this_day)
	else:
		meetup_date = meetup_date + timedelta(days=this_day-meetup_date.weekday())
	
	print "First occurence of that day is ", meetup_date
	
	#now find the right week
	if day_range == "2nd":
		meetup_date = meetup_date + timedelta(weeks=1)
	elif day_range == "3rd":
		meetup_date = meetup_date + timedelta(weeks=2)
	elif day_range == "4th":
		meetup_date = meetup_date + timedelta(weeks=3)
	elif day_range == "teenth":
		meetup_date = meetup_date + timedelta(weeks=1)
		if meetup_date.day < 13:
			meetup_date = meetup_date + timedelta(weeks=1)
	elif day_range == "last":
		while (meetup_date + timedelta(weeks=1)).day > meetup_date.day:
			meetup_date = meetup_date + timedelta(weeks=1)
	
	print "meetup_date is ", meetup_date
	
	return meetup_date
