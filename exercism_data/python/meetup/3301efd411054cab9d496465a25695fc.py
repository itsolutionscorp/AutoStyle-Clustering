"""
Utility to get the date of meetups.
Written by Bede Kelly for Exercism.
"""
import datetime
from calendar import day_name, monthrange
__author__ = "Bede Kelly"

def meetup_day(year, month, weekday, selector):
    """Returns the date of a meetup."""
    teenth_days = range(13, 20)  # 20th not included.
    weekdays = list(day_name)
    if selector == "teenth":
        for day in teenth_days:
            date = datetime.date(year, month, day)
            if weekday == weekdays[date.weekday()]:
                return date
    else:
        selectors = {
            "1st": 0,
            "2nd": 1,
            "3rd": 2,
            "4th": 3,
            "last": -1
        }
        index = selectors[selector]
        number_days = monthrange(year, month)[1]
        dates_range = range(1, number_days+1)

        all_dates = [datetime.date(year, month, day) for day in dates_range]
        possible_dates = [d for d in all_dates
                          if d.weekday() == weekdays.index(weekday)]
        return datetime.date(year, month, possible_dates[index].day)
