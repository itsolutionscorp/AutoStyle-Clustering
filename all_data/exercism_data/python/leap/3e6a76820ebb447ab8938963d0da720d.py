def is_leap_year(year):
    """ Returns True if year is a leap year, else returns False
    """
    divisible_by_400 = year % 400 == 0
    divisible_by_100 = year % 100 == 0
    divisible_by_4 = year % 4 == 0

    # leap year definition -> on every year that is evenly divisible by 4
    # except every year that is evenly divisible by 100
    # unless the year is also evenly divisible by 400
    return divisible_by_400 or (divisible_by_4 and not divisible_by_100)
