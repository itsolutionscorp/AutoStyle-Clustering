from calendar import Calendar

from datetime import date, datetime

def meetup_day(year, month, day_of_week, suffix):

    week = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
    decallage = ['1st', '2nd', '3rd', '4th', '5th']

    possible_days = [date
            for date in Calendar().itermonthdates(year, month)
            if date.month == month
            if date.weekday() == week.index(day_of_week)]

    if suffix in decallage:
        return possible_days[decallage.index(suffix)]
    elif suffix == 'teenth':
        return [x for x in possible_days if x.day >= 13 if x.day <= 19][0]
    else:
        return possible_days[-1]
