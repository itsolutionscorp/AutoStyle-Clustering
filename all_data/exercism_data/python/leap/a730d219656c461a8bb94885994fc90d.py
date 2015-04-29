'''
Created on Sep 29, 2014

@author: bennettr
'''

def is_leap_year(test):
    if test % 4 == 0:
        if test % 100 == 0 and test % 400 != 0:
            return False
        return True
    return False
