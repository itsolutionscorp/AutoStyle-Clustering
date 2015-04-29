#-------------------------------------------------------------------------------
# Name:        year
# Purpose:     exercism.io Ex 2 - Leap
#
# Author:      AFMac
#
# Created:     27/09/2014
#-------------------------------------------------------------------------------

def is_leap_year(yr):
    if (yr%4==0 and not yr%100==0) | (yr%4==0 and yr%100==0 and yr%400==0):
        return True
    return False

