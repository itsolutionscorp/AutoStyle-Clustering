from calendar import monthrange
from datetime import date


def meetup_day(year, month, weekday, type):
    weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday',
                'Saturday', 'Sunday']
    # the first day of the seven 1sts, 2nds, teenths and so on weekdays
    index = {'1st': 1,
             '2nd': 8,
             'teenth': 13,
             '3rd': 15,
             '4th': 22,
             'last': monthrange(year, month)[1] - 6}[type]

    # how many days between the first of the seven days and the wanted weekday
    delta_days = (weekdays.index(weekday)
                  - date(year, month, index).weekday())
    # if the difference is negative, go forward instead
    if delta_days < 0:
        delta_days += 7
    return date(year, month, index + delta_days)
