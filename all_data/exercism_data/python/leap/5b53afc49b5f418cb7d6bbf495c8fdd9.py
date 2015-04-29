'''This module will take in a year (positive integer) and return True if the
    year is a leap year, False if it is not.
    Lesson: Refactor.
'''

def is_leap_year(year):
    return (year%400 == 0) or ((year%100 != 0) and (year%4 == 0))
