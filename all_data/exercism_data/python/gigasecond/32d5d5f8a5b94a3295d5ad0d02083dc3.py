"""
gigasecond - a module for adding gigaseconds to datetime objects.
"""

from datetime import timedelta


def add_gigasecond(date):
    """
    Add a gigasecond to a date.
    """
    
    # Use the timedelta object.
    delta = timedelta(seconds=1000000000)
    return date + delta
