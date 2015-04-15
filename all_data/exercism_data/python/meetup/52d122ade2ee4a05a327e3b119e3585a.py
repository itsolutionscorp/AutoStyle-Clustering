import datetime
import calendar

def dayStringToDayNum(day_string):
	"""
	Converts the day name into an integer
	"""
	translation_dictionary = {'Monday':0,'Tuesday':1, 'Wednesday':2, 'Thursday':3,'Friday':4,'Saturday':5,'Sunday':6}
	
	return translation_dictionary[day_string]
	
def dayLookingFor(day_num_string):
	"""
	attempts to convert which day asked for, into an integer. If it can't, it a code instead.
	"""
	
	translation_dictionary = {'1st':1,'2nd':2,'3rd':3, '4th':4, 'last':9, 'teenth':6 }
	
	return translation_dictionary[day_num_string]
	
def meetup_day(year_int,month_int,day_string,day_num_string):
	"""
	returns today's date - to enable the calculation of next meetup
	"""
	first_day = datetime.date(year_int,month_int,1).weekday()	#what day is the first day of this month?
	
	input_day = dayStringToDayNum(day_string)		#what day am I looking for?
	
	day_difference = input_day - first_day + 7 #what is the difference between the two days
	if day_difference >6:
		day_difference-=7

	working_date = datetime.date(year_int,month_int,1+day_difference)	#the first correct day of the month
	
	day_looking_for = dayLookingFor(day_num_string)	#am i looking for 1st, 2nd, 4th or other Xday?
	
	#if looking for 1st/2nd/3rd/4th
	if day_looking_for in range(1,5):
		return working_date + datetime.timedelta(weeks = (day_looking_for-1))
		
	#if looking for 'last'
	if day_looking_for == 9:
		month_size = calendar.monthrange(year_int,month_int)[1]	#how many days in the month?
		last_date_of_month = datetime.date(year_int,month_int,month_size)#load last date in month
		last_day_of_month = last_date_of_month.weekday()	#what day is the last date?
		delta = input_day - last_day_of_month			#how many days do i have to go back to reach the correct day?
		if delta > 6:
			delta-=7
		return last_date_of_month - datetime.timedelta(days = delta)
		pass
	#if looking for 'teenth'
	if day_looking_for == 6:
		thirteenth_date = datetime.date(year_int,month_int,13)	#load the date of the 13th of the month
		thirteenth_day = thirteenth_date.weekday()	#what day is the 13th?
		delta = input_day - thirteenth_day		#how many days between 13th and the day I look for?
		if delta > 6:
			delta -=7
		return thirteenth_date + datetime.timedelta(days = delta)
	
