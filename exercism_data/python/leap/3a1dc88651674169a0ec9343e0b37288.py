#
# Solution file for leap_test.py, exercism.io
#
# v1: Initial version using modulus operator

def is_leap_year(year):
    '''
    Confirm if parameter year is a leap year.
    : criteria
    : |- on every year that is evenly divisible by 4
    :   |- except every year that is evenly divisible by 100
    :     |- unless the year is also evenly divisible by 400
    '''
    if year%4 == 0 and year%100 != 0 or year%400 == 0:
        return True
    return False
