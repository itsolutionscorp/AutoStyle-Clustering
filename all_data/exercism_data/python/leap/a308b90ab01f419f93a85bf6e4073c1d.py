def is_leap_year(year):
    '''Determine whether a given year is a leap year.'''

    # to make totally sure, we are dealing with integers 
    year = int(year)

    return bool(( not (year % 4) and (year % 100)) or not (year % 400))
