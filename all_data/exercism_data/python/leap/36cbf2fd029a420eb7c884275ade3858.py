'''
year.py

Report whether the given year is a leap year or not
'''

def is_leap_year(year):
    '''
    Return True if year is a leap year
    @param year: the year being queried
    @returns: True if it's a leap year, False if not
    '''
    if year % 4 == 0:
        if year % 400 == 0:
            return True
        if year % 100 == 0:
            return False
        return True
    return False
