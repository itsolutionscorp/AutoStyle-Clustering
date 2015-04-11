'''
Created on Mar 16, 2015

@author: rousef
'''
def is_leap_year(year):
    return (year%4 == 0) and not ((year%100 == 0) != (year%400 == 0))
# return year%4 and not (year%100 and not year%400)
