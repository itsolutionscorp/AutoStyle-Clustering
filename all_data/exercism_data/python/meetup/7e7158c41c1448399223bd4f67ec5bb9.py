"""
meetup

"""

from datetime import date, timedelta

import calendar


def _get_nth_day(year, month, day_of_week, n):
    """
    Get the nth day of a month

    :param year:
    :param month:
    :param day_of_week:
    :param n:
    :return:
    """
    month_range = calendar.monthrange(year, month)

    first_of_month = date(year, month, 1)
    delta_days = (_get_calendar_day(day_of_week) - month_range[0]) % 7
    delta_days += (7 * (n-1))
    return first_of_month + timedelta(days=delta_days)


def _get_last_day(year, month, day_of_week):
    """
    Get the last day_of_week of the month

    :param year:
    :param month:
    :param day_of_week:
    :return:
    """
    month_range = calendar.monthrange(year, month)

    last_of_month = date(year, month, month_range[1])
    delta_days = (last_of_month.weekday() - _get_calendar_day(day_of_week)) % 7
    return last_of_month - timedelta(days=delta_days)


def _get_teenth_day(year, month, day_of_week):
    """
    Get the date of the "teenth" day_of_week

    :param year:
    :param month:
    :param day_of_week:
    :return:
    """
    for n in range(13,20):
        if date(year, month, n).weekday() == _get_calendar_day(day_of_week):
            return date(year, month, n)


def _get_calendar_day(day_of_week):
    """
    Get the calendar day value from string day_of_week

    :param day_of_week:
    :return:
    """
    if 'Monday' is day_of_week:
        return calendar.MONDAY
    elif 'Tuesday' is day_of_week:
        return calendar.TUESDAY
    elif 'Wednesday' is day_of_week:
        return calendar.WEDNESDAY
    elif 'Thursday' is day_of_week:
        return calendar.THURSDAY
    elif 'Friday' is day_of_week:
        return calendar.FRIDAY
    elif 'Saturday' is day_of_week:
        return calendar.SATURDAY
    elif 'Sunday' is day_of_week:
        return calendar.SUNDAY


def meetup_day(year, month, day_of_week, param):
    """
    Get the date() of a specified day_of_week

    :param year:
    :param month:
    :param day_of_week:
    :param param:
    :return:
    """
    if '1st' == param:
        return _get_nth_day(year, month, day_of_week, 1)
    elif '2nd' == param:
        return _get_nth_day(year, month, day_of_week, 2)
    elif '3rd' == param:
        return _get_nth_day(year, month, day_of_week, 3)
    elif '4th' == param:
        return _get_nth_day(year, month, day_of_week, 4)
    elif 'last' == param:
        return _get_last_day(year, month, day_of_week)
    elif 'teenth' == param:
        return _get_teenth_day(year, month, day_of_week)
