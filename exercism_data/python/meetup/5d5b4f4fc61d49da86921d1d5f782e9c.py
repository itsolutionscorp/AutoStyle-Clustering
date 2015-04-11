__author__ = 'Cedric Zhuang'

from datetime import date, timedelta

criteria_map = {'teenth': 12,
                '1st': 0 * 7,
                '2nd': 1 * 7,
                '3rd': 2 * 7,
                '4th': 3 * 7}

weekdays = 'Monday Tuesday Wednesday Thursday Friday Saturday Sunday'.split()


def delta_day_to_next_weekday(day, weekday):
    return timedelta((weekday + 7 - day.weekday()) % 7)


def next_month_minus_one_week(month, year):
    return date(year, month + 1, 1) - timedelta(7)


def get_start_day_of_week(criteria, month, year):
    return date(year, month, 1 + criteria_map[criteria])


def meetup_day(year, month, day, criteria):
    if criteria == 'last':
        m_day = next_month_minus_one_week(month, year)
    else:
        m_day = get_start_day_of_week(criteria, month, year)

    weekday = weekdays.index(day)
    return m_day + delta_day_to_next_weekday(m_day, weekday)
