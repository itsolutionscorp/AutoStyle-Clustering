"""
Calculate the date that someone turned or will celebrate their 1 Gs anniversary.

Specification:
A gigasecond is one billion (10**9) seconds.
"""

from datetime import timedelta

def add_gigasecond(date):
    """ Calculate a time delta from seconds and add it to the required date """
    return date + timedelta(seconds=10**9)
