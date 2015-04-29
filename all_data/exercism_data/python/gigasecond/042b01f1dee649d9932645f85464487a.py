"""
Exercism Exercise 5 - gigasecond anniversary calculator
"""
from datetime import timedelta

def add_gigasecond(start_date):
    """
    Accepts datetime.date,
    Adds 10^9 seconds,
    Returns datetime.date
    """
    return start_date + timedelta(seconds=10 ** 9)
