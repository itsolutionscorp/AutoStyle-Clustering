def is_leap_year(year):
    """Returns where a year is a leap year or not."""

    return year % 4 == 0 and year % 100 != 0 or year % 400 == 0
