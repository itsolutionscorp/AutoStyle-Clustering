from calendar import monthrange, weekday
from datetime import date

days_of_week = ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')
first_possible_days = {'1st': 1, '2nd': 8, 'teenth': 13, '3rd': 15, '4th': 22}

def meetup_day(year, month, weekday_name, day_specifier):
    if day_specifier != 'last':
        first_possible_day = first_possible_days[day_specifier]
    else:
        last_day = monthrange(year, month)[1]
        first_possible_day = last_day - 6

    meetup_weekday = days_of_week.index(weekday_name)
    meetup_day_offset = meetup_weekday - weekday(year, month, first_possible_day)
    if meetup_day_offset < 0:
        meetup_day_offset += 7

    return date(year, month, first_possible_day+meetup_day_offset)
