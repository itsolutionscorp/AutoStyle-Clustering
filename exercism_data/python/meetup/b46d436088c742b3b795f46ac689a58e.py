import datetime
import calendar

class MeetupDayException(Exception): pass

def day_name_of(year, month, daynum):
    try:
        date_obj = datetime.date(year, month, daynum)
    except ValueError:
        raise MeetupDayException
    day_name = date_obj.strftime('%A')
    return day_name

def monthlen_of(year, month):
    start_day, month_len = calendar.monthrange(year, month)
    return month_len

def find_list(year, month, day_name, day_type):
    if day_type == 'teenth':
        return [13,14,15,16,17,18,19]
    elif day_type == 'last':
        month_len = monthlen_of(year, month)
        return range(month_len-6, month_len+1)
    else:
        count = int(day_type[0])
        extra = 7*(count-1)
        return range(1+extra, 8+extra)

def check_list(year, month, day_name, num_list):
    for daynum in num_list:
        if day_name_of(year, month, daynum) == day_name:
            return datetime.date(year, month, daynum)
    else: raise MeetupDayException

def meetup_day(year, month, day_name, day_type):
    num_list = find_list(year, month, day_name, day_type)
    return check_list(year, month, day_name, num_list)
