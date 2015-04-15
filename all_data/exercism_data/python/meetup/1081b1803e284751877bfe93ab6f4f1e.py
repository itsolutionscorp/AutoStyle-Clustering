import calendar
import datetime

def meetup_day(year,month,day,n):

	daydigits = {
			0:"Monday",
			1:"Tuesday",
			2:"Wednesday",
			3:"Thursday",
			4:"Friday",
			5:"Saturday",
			6:"Sunday",
			}

	teens = [13,14,15,16,17,18,19]

	last_of_month = calendar.monthrange(year,month)[1]
	end_date = datetime.date(year,month,last_of_month)
	add_day = datetime.timedelta(days=1)
	d  = datetime.date(year,month,1)

	day_count = 0
	while d  <= end_date:	
		if daydigits[d.weekday()] == day:
			day_count += 1
			day_number = int(d.strftime("%d")) 
	
			if  n == 'teenth' and day_number in teens:
				return d
			elif n == '1st' and day_count == 1:
				return d
			elif n == '2nd' and day_count == 2:
				return d
			elif n == '3rd' and day_count == 3:
				return d
			elif n == '4th' and day_count == 4:
				return d
			elif n == 'last' and day_number > (last_of_month - 7):
				return d
		d += add_day
