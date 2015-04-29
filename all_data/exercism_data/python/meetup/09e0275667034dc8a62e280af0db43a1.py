__author__ = 'djdick'
from calendar import Calendar
from datetime import date
DAYS = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']

TEENTH_DAYS = range(13, 20)

RANKS = {'1st': 0,
         '2nd': 1,
         '3rd': 2,
         '4th': 3,
         'last': -1,
         'teenth': 0  # the list will contain only one day
}

def meetup_day(year, month, day_of_week, rank_in_month):
    meetup_weekday = DAYS.index(day_of_week)
    candidates = []
    for day, weekday in Calendar().itermonthdays2(year, month):
        if day > 0 and weekday == meetup_weekday and (rank_in_month != 'teenth' or day in TEENTH_DAYS):
            candidates.append(day)

    return date(year, month, day=candidates[RANKS[rank_in_month]])
