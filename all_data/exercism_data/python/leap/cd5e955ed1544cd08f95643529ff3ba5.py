#boolean function returns if year is a leap year.
def is_leap_year(year):
    #constants
    LEAP_YEAR = 4
    SKIP_YEAR = 100
    CANCEL_SKIP_YEAR = 400
    #============
    in_year = lambda x : year % x == 0
       
    return in_year(LEAP_YEAR) and (not in_year(SKIP_YEAR) or in_year(CANCEL_SKIP_YEAR))
