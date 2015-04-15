#!/usr/bin/python
def is_leap_year(year):
    """ returns True if year is a leap year, returns False otherwise """
    if(year%4 == 0 and (year%400 == 0 or year%100!=0)):
       return True
    else:
       return False
