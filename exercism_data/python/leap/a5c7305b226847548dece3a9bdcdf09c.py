def is_leap_year(year):
    """
    Function to verify if a number is a leap year

    :param    year  Int with the year

    :returns  Bool  True if it's a leap year, False otherwise
    """
    year = int(year)
    return year % 4 == 0 and year % 100 != 0 or year % 400 == 0
