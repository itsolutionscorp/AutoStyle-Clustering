__author__ = 'dmwoods'

def is_leap_year(test):
    if (test % 4 == 0 and test % 100 != 0) or test % 400 == 0:
        return True
    return False
