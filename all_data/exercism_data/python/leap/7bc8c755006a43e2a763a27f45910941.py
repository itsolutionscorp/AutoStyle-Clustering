def is_leap_year(year):
    """
    Accepts a string or integer and returns True or False
    depending on if that year is a leap year or not
    """

    year = int(year)
    divisible_by_4 = year % 4 == 0

    if divisible_by_4:
        divisible_by_100 = year % 100 == 0
        divisible_by_400 = year % 400 == 0
        if divisible_by_100 and not divisible_by_400:
            return False
        return True
    return False
