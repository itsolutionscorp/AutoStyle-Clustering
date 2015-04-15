'''gigasecond exercise'''

from datetime import timedelta


def add_gigasecond(the_date):
    '''add a gigasecond on to the_date date and return the resultant date object'''

    a_gigasecond = timedelta(seconds=10**9)

    return the_date + a_gigasecond
