#!/usr/bin/python
from datetime import date
def meetup_day(year, month, day, position):
    day_mapping = dict(zip(range(7), ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]))
    months = [31, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    get_months = (lambda x: months[:1] + [29] + months[1:] if x % 400 == 0 or (x % 100 != 0 and x % 4 == 0) else months[:1] + [28] + months[1:])
    weekday_in_month = lambda y, m, d: filter(lambda x: x[1] == d, [(x + 1, day_mapping[date(y, m, x + 1).weekday()]) for x in range(get_months(y)[m-1])])
    position_mapping = dict(zip(["1st", "2nd", "3rd", "4th", "last", "teenth"], [lambda x: x[0], lambda x: x[1], lambda x: x[2], lambda x: x[3], lambda x: x[-1], lambda x: filter(lambda x: 12 < x[0] < 20, x)[0]]))
    return date(year, month, position_mapping[position](weekday_in_month(year, month, day))[0])
