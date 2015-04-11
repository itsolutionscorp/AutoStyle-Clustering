def is_leap_year(year):
    """
    takes an integer year as its argument
    returns true if year represents a leap year
    """

    return year % 4 == 0 and (year % 100 != 0 or year % 400 == 0)
