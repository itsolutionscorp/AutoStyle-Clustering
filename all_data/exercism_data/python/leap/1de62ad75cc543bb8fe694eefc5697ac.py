def is_leap_year(year):
    year_divisible_by = lambda x: year % x == 0

    if (year_divisible_by(400) or
            (year_divisible_by(4) and not year_divisible_by(100))):
        return True
    else:
        return False
