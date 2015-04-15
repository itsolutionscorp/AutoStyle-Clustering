__author__ = 'Cedric Zhuang'

from datetime import date, timedelta

criteria_map = {'t': 12,
                '1': 0 * 7,
                '2': 1 * 7,
                '3': 2 * 7,
                '4': 3 * 7}

day_map = {'Monday': 0,
           'Tuesday': 1,
           'Wednesday': 2,
           'Thursday': 3,
           'Friday': 4,
           'Saturday': 5,
           'Sunday': 6}


def meetup_day(year, month, day, criteria):
    criteria = criteria[0]
    if criteria == 'l':
        m_day = date(year, month + 1, 1) - timedelta(7)
    else:
        m_day = date(year, month, 1 + criteria_map[criteria])

    while m_day.weekday() != day_map[day]:
        m_day += timedelta(1)

    return m_day
