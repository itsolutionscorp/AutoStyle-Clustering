import calendar

__author__ = 'grdunn'

import datetime

day_of_week = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
occurences_num = {'1st': 1, '2nd': 2, '3rd': 3, '4th': 4}
occurences_other = ['last', 'teenth']

def meetup_day(year, month, dow, instance):
    """


    :param year:
    :param month:
    :param dow:
    :param instance:
    :rtype : date
    """

    base_dow = 0
    last_dow = 0

    # What's the first day of that month?
    first_day, max_days = calendar.monthrange(year, month)

    # The first instance of the desired dow
    base_dow = (first_day - day_of_week[dow]) % 7

    if instance in occurences_num.keys():
        return datetime.date(year, month, first_dow * occurences_num[instance])

    if instance == 'last':
        last_dow = datetime.date(year, month, max_days).weekday()
        return datetime.date(year, month, max_days - ((last_dow - day_of_week[dow]) % 7))
