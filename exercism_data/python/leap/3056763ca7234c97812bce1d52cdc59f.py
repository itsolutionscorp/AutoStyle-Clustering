def is_leap_year(year_in):

    bool_leap_year = (year_in % 4 == 0 and 
                     (not(year_in % 100 == 0) or 
                      year_in % 400 == 0))

    return bool_leap_year 
