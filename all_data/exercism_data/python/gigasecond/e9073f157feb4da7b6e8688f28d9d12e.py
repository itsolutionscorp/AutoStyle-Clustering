import datetime

#calculate the Gs anniversary for a given date of birth (dob)
def add_gigasecond(dob):
	#number of seconds worked with
	secs_left = 10**9

	#number of seconds in a day
	num_sec_day = 86400
	#finds number of days to add to dob
	days = int(secs_left/num_sec_day)
	secs_left %= num_sec_day

	'''
	this code makes the timedelta more accurate but isn't necessary becase the date obj is only accurate to the day

	#number of seconds in hour
	num_sec_hour = 3600
	#finds number of hours to add to dob
	hours = int(secs_left/num_sec_hour)
	secs_left %= num_sec_hour

	#number of seconds in a minute
	num_sec_minute = 60
	#finds number of minutes to add to dob 
	minutes = int(secs_left/num_sec_minute)
	secs_left %= num_sec_minute

	#left over seconds go to seconds to add to dob
	seconds = secs_left

	#add change(the time of a gigasecond) to dob
	change = datetime.timedelta(days, hours, minutes, seconds)
	'''
	change = datetime.timedelta(days)
	anniversary = dob+change

	return anniversary
