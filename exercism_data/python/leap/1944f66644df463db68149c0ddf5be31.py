def is_leap_year(year):
    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 == 0:
                return True  # divisible by 4, 100 and 400
            return False  # divisible by 4 and 100
        return True  # divisible by 4, and not 100
    return False  # not divisible by 4
