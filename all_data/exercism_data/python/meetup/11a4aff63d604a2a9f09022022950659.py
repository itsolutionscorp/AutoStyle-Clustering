from datetime import date, timedelta

days_of_week = ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')
first_possible_day = {'1st': 1, '2nd': 8, 'teenth': 13, '3rd': 15, '4th': 22}

def meetup_day(year, month, weekday_name, day_specifier):
    if day_specifier != 'last':
        first_possible_date = date(year, month, first_possible_day[day_specifier])
    else:
        if month != 12:
            first_possible_date = date(year, month+1, 1) - timedelta(7)
        else:
            first_possible_date = date(year, 12, 24)
    
    meetup_weekday = days_of_week.index(weekday_name)
    meetup_day_offset = meetup_weekday - first_possible_date.weekday()
    if meetup_day_offset < 0:
        meetup_day_offset += 7
    return first_possible_date + timedelta(meetup_day_offset)
