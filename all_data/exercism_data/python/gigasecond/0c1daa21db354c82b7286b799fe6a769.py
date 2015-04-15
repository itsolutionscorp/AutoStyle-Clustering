from datetime import timedelta

GIGASECOND = timedelta(0, 10**9)


def add_gigasecond(d):
    '''
    returns date with 1 gigasecond added
    '''
    return d + GIGASECOND
