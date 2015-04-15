'''
Module to add gigasecond to a given date.
'''

from datetime import timedelta

def add_gigasecond(inpt_date):
    '''
    Add gigasecond to given date.
    Input: datetime object
    Output: Input + 1 billion seconds datetime object
    '''

    return inpt_date + timedelta(seconds=10**9)
