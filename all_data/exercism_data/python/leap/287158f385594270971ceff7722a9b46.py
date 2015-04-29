def is_leap_year(year):
"""Is the year a leap year

    Args:
    year: the desired year as an integer

    Return:
    Boolean if the year is a leap year or not
"""
    is_4th = year % 4 == 0
    is_100th = year % 100 == 0
    is_400th = year % 400 == 0

    if is_400th:
        return True
    if is_100th:
        return False
    if is_4th:
        return True
    return False
