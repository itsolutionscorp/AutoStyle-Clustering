from datetime import timedelta, datetime

def add_gigasecond(birthdate):
    '''
    Returns date when person with
    a given birthdate will reach their
    gigasecond anniversary
    '''
    return birthdate + timedelta(seconds=10**9)
