"""
Program to determine if year is a leap year.
"""
import re


def is_leap_year(year):
    """
    Function to accept year as argument
    and return true or false depending on whether year is leap year.
    """
    try:
        pattern = re.compile('([\d]{4})')
        if pattern.match(str(year)):
            if not year % 4 and year % 100 or not year % 400:
                return True
            else:
                return False
    except ValueError:
        print('Four-digit integer not supplied.')
