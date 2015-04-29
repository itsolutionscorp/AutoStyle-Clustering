from datetime import date, timedelta
from calendar import monthrange

# Deprecated
#class MeetupDayException(Exception):
    #pass

class MeetupDayException(Exception):
    def __init__(self, message):
        super(MeetupDayException, self).__init__(message)
        self.message = message

def meetup_day(year, month, day_name, ordinal):
    day_of_month = parse_ordinal(year, month, day_name, ordinal)
    return date(year,month,1) + timedelta(days = day_of_month - 1)

def parse_ordinal(year, month, day_name, ordinal):
    if ordinal == 'teenth':
        return teenth_of_month(year, month, day_name)
    elif ordinal == 'last':
        return last_of_month(year, month, day_name)
    else:
        return nth_of_month(year, month, day_name, ordinal)

def weekday_name(year, month, day):
    names = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
    return names[date(year, month, day).weekday()]

def teenth_of_month(year, month, day_name):
    for day in range(13,20):
        if day_name == weekday_name(year, month, day):
            return day
    # Something is seriously wrong - probably day_name is spelt wrong
    raise MeetupDayException('No %s found between 13 - 19' % day_name)

def last_of_month(year, month, day_name):
    _, last_day = monthrange(year, month)
    for day in range(last_day - 6, last_day + 1):
        if day_name == weekday_name(year, month, day):
            return day
    # Something is seriously wrong - probably day_name is spelt wrong
    raise MeetupDayException('No %s found between %d - %d' % (last_day-6, last_day))

def nth_of_month(year, month, day_name, ordinal):
    first_day = 0
    n = int(ordinal[:-2])
    for day in range(1, 8):
        if day_name == weekday_name(year, month, day):
            first_day = day
            break
    try:
        nth = first_day + 7*(n-1)
        nth_date = date(year, month, nth)
        return nth
    except:    
        raise MeetupDayException('The %s %s is not a valid date' % (ordinal, day_name))
