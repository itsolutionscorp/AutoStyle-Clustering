'''leap year utility'''


def is_leap_year(year):
    '''return True if a leap year'''
    is_leap = False                     # assume not a leap year
    if evenly_divisible(year, 4):       # /4 then leap year
        is_leap = True
    if evenly_divisible(year, 100):     # /100 not a leap year
        is_leap = False
    if evenly_divisible(year, 400):     # /400 a leap year
        is_leap = True

    return is_leap

def evenly_divisible(numerator, denominator):
    '''return True if numerator evenly divisible by denominator'''
    return numerator % denominator == 0
