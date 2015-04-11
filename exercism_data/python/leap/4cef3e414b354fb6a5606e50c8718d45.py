# -*- coding: utf-8 -*-
"""
Created on Mon Oct 27 22:08:34 2014

@author: DiCar
"""

        
#NOTE: mathematical requirements for defining a leap year
#        on every year that is evenly divisible by 4
#  except every year that is evenly divisible by 100
#    unless the year is also evenly divisible by 400

#Function used to determine if a particular year is a leap year
def is_leap_year(year):
# if the year is divisible by 400 it is a leap year, if not, then next check next conditional
    if year%400 == 0:
        return True
        
# if the year is divisible by 100 then it is not a leap year. If not divisible, check next conditional      
    elif year%100 == 0:
        return False
        
# if the year is divisible by 4 then it is a leap year. If not, check next conditional
    elif year%4 == 0:
        return True

#if the year is not divisible by 4 then it is not a leap year
    elif year%4 != 0:
        return False

        
    
