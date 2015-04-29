'''A simple Boolean evaluation function to test for leap years'''

def is_leap_year(year):
    '''Accepts an integer year value, and returns a Boolean indicating
    whether or not the year is a leap year'''
    return (year%400) == 0 \
           or (year%4) == 0 \
           and not (year%100) == 0
