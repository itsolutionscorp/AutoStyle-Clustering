#-------------------------------------------------------------------------------
# Name:        year.py
# Purpose:     Determines leap years.
#
# Author:      Mihaela
#
# Created:     25.09.2014
#-------------------------------------------------------------------------------
def is_leap_year(given_year):
    if((given_year % 4 == 0) and
       (((given_year % 100 != 0) and (given_year % 400 != 0)) or
       ((given_year % 100 == 0) and (given_year % 400 == 0)))):
         return True
    else:
         return False
