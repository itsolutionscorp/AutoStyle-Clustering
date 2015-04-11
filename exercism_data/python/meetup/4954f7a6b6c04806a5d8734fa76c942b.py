# meetup.py

from datetime import date
from calendar import monthrange
from datetime import date


def meetup_day(m_year, m_month, day_str, str_ordinal):
    ''' takes a year, month, day, and ordinal string 1st 2nd 3rd 4th
teenth '''
    if str_ordinal == "teenth":
        # "Teenth" days are 13 - 19, so in range(13, 20)
        end_week = 20
    elif str_ordinal == "last":
        # Need to add 1 to ensure last day of month is checked in for loop
        end_week = monthrange(m_year, m_month)[1]+1
    else:
        end_week = int(str_ordinal[0])*7+1
    start_week = end_week - 7
    for x in range(start_week, end_week):
        if date(m_year, m_month, x).strftime('%A') == day_str:
            return date(m_year, m_month, x)
