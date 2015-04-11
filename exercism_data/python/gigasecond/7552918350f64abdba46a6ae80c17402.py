"""
gigasecond.py: program that will calculate the date
that someone turned or will celebrate their 1 Gs anniversary
"""
from datetime import timedelta


def add_gigasecond(date):
    """
    Function to accept a datetime date object
    and add a gigasecond (10**9 seconds)

        date: A datetime.date object
    """
    # Calculate our gigasecond
    gigasecond = timedelta(seconds=10**9)
    return date + gigasecond
