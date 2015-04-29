#!/usr/bin/env python3
from datetime import date, timedelta
from calendar import monthrange

week = ['Monday',
        'Tuesday',
        'Wednesday',
        'Thursday',
        'Friday',
        'Saturday',
        'Sunday']

order = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3,
         '5th': 4, 'last': 4}

class MeetupDayException(Exception):
    pass

def meetup_day(year, month, dayofweek, meet):
    m_date = date(year, month, 1)
    month_max_days = monthrange(year, month)[1]
    dayofweek_number = week.index(dayofweek)
    m_days = m_date.weekday() - dayofweek_number
    if m_days == 0:
        m_date = m_date + timedelta(days=(order[meet]) * 7)
        return m_date
    if m_days > 0:
        order['teenth'] = 1
        plus_days = timedelta(days=(order[meet] + 1) * 7 - abs(m_days))
        if plus_days.days < month_max_days:
            return m_date + plus_days
        else:
            raise MeetupDayException
    if m_days < 0:
        order['teenth'] = 2
        plus_days = timedelta(days=(order[meet]) * 7 + abs(m_days))
        if plus_days.days < month_max_days:
            return m_date + plus_days
        else:
            raise MeetupDayException
