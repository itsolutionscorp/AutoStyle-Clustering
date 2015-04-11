# gigasecond.py
# exercism: Python Exercise #6
from datetime import datetime, timedelta


def add_gigasecond(day):
    """Returns the datetime + 1 gigasecond"""

    return day + timedelta(seconds=10**9)
