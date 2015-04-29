def is_leap_year(year):
    """Returns true if 'year' is a leap year. Otherwise, returns false."""

    return (year % 400 == 0) or ((year % 4 == 0) and (year % 100 != 0))
