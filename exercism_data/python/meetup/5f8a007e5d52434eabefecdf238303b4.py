import calendar
from datetime import date, timedelta

class MeetupDayException(Exception):
    pass

def nth_date(dates, pos):
    """
    from a list of weekdays in the month, pick the n-th date.
    raise the error if list if empty or there's no such date
    """
    if not dates or pos >= len(dates): raise MeetupDayException("")
    return dates[pos]
    
def weekdays(basedate, weekday ):
    """
    return a list of dates in the month of the `base_date`, having the same weekday as `weekday`
    """
    wd = list(calendar.day_name).index(weekday) # get the index of the `weekday` ("Monday"-->0)
    dates = []
    for pos in range(6):
	day_diff =  7*pos + (wd - basedate.weekday())
	date = basedate + timedelta(days = day_diff)
	if date.month == basedate.month: dates.append(date)
    return dates

def meetup_day(year, month, weekday, position):
    basedate = date(year, month, 13) if position == "teenth" else date(year, month, 1)
    dates = weekdays(basedate, weekday)
    if position == "teenth":
	pos = 0
    elif position == "first":
	pos = 0
    elif position == "last":
	pos = -1
    else:
	pos = int(position[0]) - 1 # 1st, 2nd etc    
    return nth_date(dates,pos)
