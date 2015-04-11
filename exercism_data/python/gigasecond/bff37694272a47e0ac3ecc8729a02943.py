'''
Gigasecond handling
'''

from datetime import date, timedelta

GIGASECOND = timedelta(seconds=10**9)


def add_gigasecond(startdate):
    '''
    Returns a date 10**9 seconds from the given start date
    '''
    if not isinstance(startdate, date):
        raise TypeError('startdate must be a date')

    return startdate + GIGASECOND
