'''
Created on Sep 23, 2014

@author: dhawkins
'''
from __future__ import division

def is_leap_year(year):
    
    div_by_4 = year % 4 == 0
    div_by_100 = year % 100 == 0
    div_by_400 = year % 400 == 0

    if div_by_4 and not div_by_100:
        return True
    elif div_by_4 and div_by_400:
        return True
     
    return False

if __name__ == '__main__':
    pass
