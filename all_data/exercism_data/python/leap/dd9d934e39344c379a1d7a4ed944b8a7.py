# year.py
# exercism.io: Python Exercise #2


def is_leap_year(year):
    """Determines if a year is a leap year or not."""

    # if the year is divisible by 400, it is a leap year
    if year % 400 == 0:
        return True

    # if the year is divisible by 100 but not 400, its not a leap year
    if year % 100 == 0:
        return False

    # if the year is divisible by 4 but not 100, its a leap year
    if year % 4 == 0:
        return True

    # if the year is not divisible by 4, its not a leap year
    return False
