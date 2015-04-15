"""This module contains a function to test whether a year is a leap year."""

def is_leap_year(year):
    """Return True if year is a leap year, False otherwise"""
    if year % 400 == 0:
        return True
    if year % 100 == 0:
        return False
    if year % 4 == 0:
        return True
    return False
