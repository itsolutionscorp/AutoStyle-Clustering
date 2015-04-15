#!/usr/bin/python
#############################################
#
# File Name : year.py
#
# Last Modified : Sun 26 Oct 2014 05:43:04 PM PDT
#
#############################################

'''
on every year that is evenly divisible by 4    
  except every year that is evenly divisible by 100    
      unless the year is also evenly divisible by 400   
'''

def is_leap_year(year):
    isLeap = False

    if year % 4 == 0:
        isLeap = True
        if year % 100 == 0:
            isLeap = False
            if year % 400 == 0:
                isLeap = True

    return isLeap
