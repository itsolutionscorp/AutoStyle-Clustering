from collections import defaultdict
from datetime import date, timedelta

def meetup_day(year, month, day_name, date_type):
    weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday',
                'Friday', 'Saturday', 'Sunday']
    operators = {
        '1st': lambda x: x[0],
        '2nd': lambda x: x[1],
        '3rd': lambda x: x[2],
        '4th': lambda x: x[3],
        'last': lambda x: x[-1],
        'teenth': lambda x: [d for d in x if 13 <= d <= 19][0],
    }

    data = defaultdict(list)
    day = date(year=year, month=month, day=1)
    while day.month == month:
        data[weekdays[day.weekday()]].append(day.day)
        day += timedelta(1)

    return date(year=year, month=month, day=operators[date_type](data[day_name]))
