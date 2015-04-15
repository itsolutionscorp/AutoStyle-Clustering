__author__ = 'jimblackler'

from datetime import date
from dateutil.relativedelta import relativedelta


def meetup_day(year, month, day_name, relative):

    calc_date = date(year, month, 1)
    if relative == 'teenth':
        calc_date += relativedelta(days=13 - 1)
    elif relative == '2nd':
        calc_date += relativedelta(weeks=2 - 1)
    elif relative == '3rd':
        calc_date += relativedelta(weeks=3 - 1)
    elif relative == '4th':
        calc_date += relativedelta(weeks=4 - 1)
    elif relative == 'last':
        calc_date += relativedelta(months=1)
        calc_date -= relativedelta(weeks=1)

    weekday = {'Monday': 0,
               'Tuesday': 1,
               'Wednesday': 2,
               'Thursday': 3,
               'Friday': 4,
               'Saturday': 5,
               'Sunday': 6}[day_name]
    days_short = weekday - calc_date.weekday()
    if days_short < 0:
        days_short += 7
    calc_date += relativedelta(days=days_short)
    return calc_date
