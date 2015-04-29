def is_leap_year(year):
    return (year % 4 == 0) \
           and (year % 400 == 0 if year % 100 == 0 else True)
