from datetime import date

def meetup_day(yy, mm, dow, nth):
	'''
	Calculate the date of meetups, which are, e.g., the first Monday, the last Thursday, or the teenth Wednesday
	of the month
	'''
	ordlist = {
		"1st": 0,
		"2nd": 1,
		"3rd": 2,
		"4th": 3,
		"last": -1
	}
	weekdays = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
	
	date_of_1st_dow = (weekdays.index(dow) - weekdays.index(date(yy, mm, 1).strftime("%A")) + 7) % 7 + 1
	try:
		date_of_last_dow = date_of_1st_dow + 28
		date(yy, mm, date_of_last_dow)
	except ValueError:
		date_of_last_dow = date_of_1st_dow + 21
	dow_list = list(range(date_of_1st_dow, date_of_last_dow+1, 7))	
		
	if nth in ordlist:
		return date(yy, mm, dow_list[ordlist[nth]])
	elif nth == "teenth":
		teenthday = [day for day in dow_list if day in range(13,20)]
		return date(yy, mm, teenthday[0])
	else:
		return False
	
