__author__ = 'grdunn'

import calendar
import datetime


def meetup_day(year, month, dow, instance):
    """
    :param year:
    :param month:
    :param dow:
    :param instance:
    :rtype : date
    """

    day_of_week = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
    occurences_num = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3}

    first_day, max_days = calendar.monthrange(year, month)

    # The first instance of the desired dow
    base_dow = ((day_of_week[dow] - first_day) % 7) + 1

    # If we're looking for a multiple, take the base day and add however many weeks
    if instance in occurences_num.keys():
        return datetime.date(year, month, base_dow + (occurences_num[instance] * 7))

    # subtract the delta of the index of the days from the end of the month
    if instance == 'last':
        last_dow = datetime.date(year, month, max_days).weekday()
        return datetime.date(year, month, max_days - ((last_dow - day_of_week[dow]) % 7))

    if instance == 'teenth':
        # find the instance of the day where the number is between 13-19 (inclusive)
        if 13 <= (base_dow + 7) <= 19:
            return datetime.date(year, month, base_dow + (1 * 7))
        return datetime.date(year, month, base_dow + (2 * 7))
