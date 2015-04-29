import datetime
import calendar
import re

def cardinal(ordinal):
    return int(''.join([char for char in ordinal if char.isdigit()]))

def meetup_day(year, month, day_of_week, ordinal):
    days = {
        0: 'Monday',
        1: 'Tuesday',
        2: 'Wednesday',
        3: 'Thursday',
        4: 'Friday',
        5: 'Saturday',
        6: 'Sunday'
    }

    possible_days = []

    number_of_days = calendar.monthrange(year, month)[1]

    days_of_month = [datetime.date(year, month, 1) + datetime.timedelta(days=x) for x in range(0, number_of_days)]

    for day in days_of_month:
        if days[day.weekday()] == day_of_week:
            possible_days.append(day.day)

    if ordinal == 'teenth':
        for x in possible_days:
            if 10 < x < 20:
                day_of_month = x
    elif ordinal == 'last':
        day_of_month = possible_days[-1]
    else:
        day_of_month = possible_days[cardinal(ordinal)-1]

    return datetime.date(year, month, day_of_month)
