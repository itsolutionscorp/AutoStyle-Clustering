import datetime
from calendar import monthrange

def meetup_day(meetup_year, meetup_month, meetup_weekday, meetup_weekday_occurance):

	#create dictionary translating days of week into integers
	day_of_week = { 'Monday':0, 
					'Tuesday':1, 
					'Wednesday':2, 
					'Thursday':3,
					'Friday':4, 
					'Saturday':5, 
					'Sunday':6}

		#calculate number of days in month
	days_in_month = monthrange(meetup_year, meetup_month)[1]

	#create list of dates for all days of the given weekday for the given month
	dates_of_given_weekday = []
	day = 1
	while day <= days_in_month:
		day_in_question = datetime.date(meetup_year, meetup_month, day)
		if day_in_question.weekday() == day_of_week[meetup_weekday]:
			dates_of_given_weekday.append(day)
		day+=1

	#translate human-readable input into date object and return
	if meetup_weekday_occurance == 'teenth':
		for date in dates_of_given_weekday:
			if date > 12 and date < 20:
				return datetime.date(meetup_year, meetup_month, date)

	elif meetup_weekday_occurance == '1st':
		return datetime.date(meetup_year, meetup_month, dates_of_given_weekday[0])

	elif meetup_weekday_occurance == '2nd':
		return datetime.date(meetup_year, meetup_month, dates_of_given_weekday[1])

	elif meetup_weekday_occurance == '3rd':
		return datetime.date(meetup_year, meetup_month, dates_of_given_weekday[2])

	elif meetup_weekday_occurance == '4th':
		return datetime.date(meetup_year, meetup_month, dates_of_given_weekday[3])

	elif meetup_weekday_occurance == 'last':
		return datetime.date(meetup_year, meetup_month, dates_of_given_weekday[-1])
