def is_leap_year(year):
    '''Checks if a given year is a leap year.'''
    return year % 4 == 0 and year % 100 != 0 or year % 400 == 0
