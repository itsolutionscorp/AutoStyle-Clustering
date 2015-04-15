import datetime
import calendar

dow = dict(zip('Monday Tuesday Wednesday Thursday Friday Saturday Sunday'.split(),
           range(7)))


def meetup_day(year, month, day, occurrence):
	if occurrence == "last":
		day = datetime.datetime(year, month, calendar.monthrange(year, month)[1]).day
		return datetime.date(year,month,datetime.datetime(year, month, calendar.monthrange(year, month)[1]).day)
	elif occurrence[0:1].isdigit():
		first_day_of_month = datetime.datetime(year,month,1).weekday()
		if dow[day] >= first_day_of_month:
			dist_to_dow = dow[day]-first_day_of_month
		else:
			dist_to_dow = dow[day]-first_day_of_month+7
		return datetime.date(year,month,dist_to_dow+1+(7*(int(occurrence[0:1])-1)))
	elif occurrence == 'teenth' : 
		# I assume that 'teenth' means that is the first apperance between 13th and 19th of each month
		first_day_of_month = datetime.datetime(year,month,13).weekday()
		if dow[day] >= first_day_of_month:
			dist_to_dow = dow[day]-first_day_of_month
		else:
			dist_to_dow = dow[day]-first_day_of_month+7
		return datetime.date(year,month,dist_to_dow+13)		
		
		
