"""
Write a program that will calculate the date that someone turned or will celebrate their 1 Gs anniversary.

A gigasecond is one billion (10**9) seconds.
"""

from datetime import date, timedelta


def add_gigasecond(birthdate):
    """
    Calculate gigasecond anniversary
    Takes date in 2043, 1, 1 format
    returns date object
    """

    tdelta = timedelta(seconds=+10**9)
    gs_anniversary = birthdate + tdelta

    return gs_anniversary
