'''
gigasecond.py

Calculates the date that someone turned or will celebrate their 1 Gs anniversary
'''
from datetime import date, timedelta

def add_gigasecond(birthdate):
    '''
    Given a date someone was born, return the date that person turns 1Gs

    @param birthdate: the given birthday
    @return: date of the 1 Gs anniversary (10**9 seconds)
    '''
    Gs = 10**9
    return birthdate + timedelta(seconds=Gs)
