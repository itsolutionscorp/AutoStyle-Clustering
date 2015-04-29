def is_leap_year(year):
    ''' determines if a given year is leap or not
    '''

    response = False

    if (year % 100 is 0) and (year % 400 is not 0):
        response = False
    elif (year % 4 is 0):
        response = True

    return response
