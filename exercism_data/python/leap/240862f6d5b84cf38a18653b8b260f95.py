def is_leap_year(year):

    is_leap = False

    is_divisible_by_4 = year % 4 == 0
    is_divisible_by_100 = year % 100 == 0
    is_divisible_by_400 = year % 400 == 0

    if is_divisible_by_4:
        if not is_divisible_by_100 or is_divisible_by_400:
            is_leap = True

    return is_leap
