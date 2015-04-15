"""
year
A module that handles calculations related to years.
Created for the "leap" exercism.io course.
"""

def is_leap_year(year):
    if year % 4 == 0 and (year % 400 == 0 or year % 100 != 0):
        return True
    else:
        return False
