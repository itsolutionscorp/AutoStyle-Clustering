def is_leap_year(year):
    """Returns true if 'year' is a leap year. Otherwise, returns false."""

    # All leap years are divisble by 4.
    if year % 4 == 0:
        # A leap year is not divisible by 100, unless it is also divisble by
        # 400.
        if year % 100 == 0:
            return year % 400 == 0

        else:
            return True

    else:
        return False
