def is_leap_year(year):
    ''' Is it a leap year? '''

    div_by_4 = (year % 4) == 0
    div_by_100 = (year % 100) == 0
    div_by_400 = (year % 400) == 0

    return div_by_400 or (div_by_4 and not div_by_100)
