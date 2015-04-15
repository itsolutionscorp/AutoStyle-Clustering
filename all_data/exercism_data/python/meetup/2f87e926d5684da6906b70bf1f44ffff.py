from datetime import date


def meetup_day(year, month, day, teenth):
    day_num = {
        'Monday': 1,
        'Tuesday': 2,
        'Wednesday': 3,
        'Thursday': 4,
        'Friday': 5,
        'Saturday': 6,
        'Sunday': 7,
    }[day]

    forward = {
        '2nd': 2,
        '3rd': 9,
        '4th': 16,
        'last': 23,
        'teenth': 'teenth'
    }.get(teenth, 0)

    if forward == 'teenth':
        forward = 10
    else:
        forward += day_num

    while date(year, month, forward).weekday() != day_num - 1:
        forward += 1

    return date(year, month, forward)
