def is_leap_year(year):
    ''' determines if a given year is leap or not
    '''

    return ((year % 400 is 0) or
        ((year % 4 is 0) and (year % 100 is not 0)))
