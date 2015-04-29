def is_leap_year(year):
    """
    Check if year is a leap year.

    :year: int
    :returns: True or False
    
    """
    return not year % 4 and not(not year % 100 and year % 400)
