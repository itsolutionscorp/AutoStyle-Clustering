def is_leap_year(year):
    # A leap year is on every year that is evenly divisible by 4.
    # Except every year that is evenly divisible by 100.
    # Unless the year is also evenly divisible by 400.
    return year % 4 == 0 and (year % 100 != 0 or year % 400 == 0)
