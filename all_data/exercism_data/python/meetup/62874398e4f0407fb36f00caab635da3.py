import calendar
from datetime import date


"""Map of the day of week to its number """
_DAY_MAP = dict([ (day, idx) for (idx,day) in enumerate(calendar.day_name)])


"""Map of the week position to array position"""
_WEEK_MAP = {
        '1st': 0,
        '2nd': 1,
        '3rd': 2,
        '4th': 3,
        '5th': 4,
        'first': 0,
        'second': 1,
        'third': 2,
        'fourth': 3,
        'fifth': 4,
        }


"""Find the correct day of the month, given year, month, day of week, 
and week number.
"""
#TODO: Out of Range errors?
def meetup_day(year, month, day_of_week, week):
    dow = _DAY_MAP[day_of_week]
    c = calendar.monthcalendar(year, month)
    weekdays = [ w[dow] for w in c if w[dow] != 0 ]
    if week in _WEEK_MAP:
        return date(year,month, weekdays[_WEEK_MAP[week]])
    elif week == 'last':
        return date(year,month, weekdays[-1])
    elif week == 'teenth':
        for d in weekdays:
            if d in range(13,20):
                return date(year,month,d)
    return None
