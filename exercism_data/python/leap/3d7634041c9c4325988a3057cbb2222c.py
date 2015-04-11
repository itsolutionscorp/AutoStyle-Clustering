from functools import partial

#helper function to improve readability
def evenly_div(numerator, denominator):
    return numerator % denominator == 0

#boolean function returns if year is a leap year.
def is_leap_year(year):
    #constants
    LEAP_YEAR = 4
    SKIP_YEAR = 100
    CANCEL_SKIP_YEAR = 400
    #============
    #binding the first argument of evenly_div to improve readability
    in_year = partial(evenly_div, year)

    return in_year(LEAP_YEAR) and (not in_year(SKIP_YEAR) or in_year(CANCEL_SKIP_YEAR))
