def is_leap_year(year):
    """Returns 'True' if year is divisible by 4 but not by 100
        or year is divisible by 400"""

    return (year % 4 == 0 and year % 100 != 0) or year % 400 == 0
