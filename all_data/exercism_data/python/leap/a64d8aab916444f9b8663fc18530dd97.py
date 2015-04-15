'''
Created on Sep 26, 2014

@author: jbarni00
'''
def is_leap_year(yearval):
    if (yearval % 400 == 0):
        return True
    if  (yearval % 100 == 0):
        return False
    if (yearval % 4 == 0):
        return True
    return False
        
