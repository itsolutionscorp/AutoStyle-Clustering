from datetime import timedelta
__author__ = 'greg'

G = timedelta(seconds=10**9) 

def add_gigasecond(start_date):
    """takes a datetime-format start_date, adds 10**9 seconds, returns the resulting
    date"""
    return start_date + G
