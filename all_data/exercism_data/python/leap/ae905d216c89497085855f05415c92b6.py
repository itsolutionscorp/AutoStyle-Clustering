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
       # Leap year type is detected
         return True
    else:
         # The given year does not match the leap year conditions
         return False
