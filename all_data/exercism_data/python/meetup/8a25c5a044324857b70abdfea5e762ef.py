# Meetup Dates

import calendar, datetime

class MeetupDayException(Exception):
	pass

def meetup_day(year, month, day, day_in_month):
	days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
	if (day_in_month == 'teenth'):
		day_offset = 13
	elif (day_in_month == 'last'):
		day_offset = calendar.monthrange(year, month)[1] - 6
	else:
		day_offset = max(1, (int(day_in_month[0])-1)*7)
	start_day = datetime.date(year, month, day_offset)
	delta = datetime.timedelta(days=(days.index(day)-start_day.weekday())%7)
	meetupday = start_day + delta 
	
	if (meetupday.month != month):
		raise MeetupDayException(Exception)
	
	return start_day + delta
