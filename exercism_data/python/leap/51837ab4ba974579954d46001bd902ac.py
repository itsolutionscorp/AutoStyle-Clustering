def is_leap_year(year):
    '''Determine whether a given year is a leap year.'''

    return bool(( not (year % 4) and (year % 100)) or not (year % 400))
