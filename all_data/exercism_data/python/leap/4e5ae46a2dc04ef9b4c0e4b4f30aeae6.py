# -*- coding: utf-8 -*-
#
## Leap
#
#Write a program that will take a year and report if it is a leap year.##
#
#The tricky thing here is that a leap year occurs:
#
#'``plain
#on every year that is evenly divisible by 4
#  except every year that is evenly divisible by 100
#    unless the year is also evenly divisible by 400
#```

def is_leap_year(year):

    #assume not a leap year
    result = False
    
    #run checks in order
    if year % 4 == 0 :
        result = True
    if year % 100 == 0 :
        result = False
    if year % 400 == 0 :
        result = True
    
    return result
