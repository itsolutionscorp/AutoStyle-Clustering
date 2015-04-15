def is_leap_year(year):
    return (year % 4 == 0) & (year % 100 != 0 or year % 400 == 0)
