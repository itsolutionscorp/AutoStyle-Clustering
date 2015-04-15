#-------------------------------------------------------------------------------
# Name:        year.py
# Purpose:     Determines leap years.
#
# Author:      Mihaela
#
# Created:     25.09.2014

# Conditions for leap year:
# every year that is evenly divisible by 4
# except every year that is evenly divisible by 100
# unless the year is also evenly divisible by 400
#-------------------------------------------------------------------------------
def is_leap_year(given_year):
    # Leap year detection code
    return ((given_year % 4 == 0) and
           (((given_year % 100 != 0) and (given_year % 400 != 0)) or
           ((given_year % 100 == 0) and (given_year % 400 == 0))))
