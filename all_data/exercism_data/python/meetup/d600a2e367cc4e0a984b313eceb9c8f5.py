#
# Full disclosure: I got some help on stackoverflow with this one,
# but tried to understand the process as thoroughly as possible
# and learned a lot in the process.

import datetime
import calendar

def meetup_day(year, month, meetup_day, week_ref):
    day_int = list(calendar.day_name).index(meetup_day)
    relevant_mdays = [i for i in
                      list(zip(*calendar.monthcalendar(year,month)))[day_int]
                      if i != 0]
    if week_ref == 'teenth':
        mday = next((i for i in relevant_mdays if i in range(13,20)),None)
    else:
        if week_ref == 'last':
            week_int = -1
        else:
            week_int = int(week_ref[0])-1
        mday = relevant_mdays[week_int]
    return datetime.date(year, month, mday)    
