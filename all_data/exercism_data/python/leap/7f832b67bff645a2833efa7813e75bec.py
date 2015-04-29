"""
  File for the Python "Leap" exercise.
"""

def is_leap_year(year):
    """ Checks whether or not the given year is leap year. """
    return year % 4 == 0 and year % 100 != 0 or year % 400 == 0
