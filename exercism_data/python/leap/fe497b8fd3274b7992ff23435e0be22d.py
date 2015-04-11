def is_leap_year(year):
    year_divisible_by_4 = not (year % 4)
    year_divisible_by_100 = not (year % 100)
    year_divisible_by_400 = not (year % 400)

    if year_divisible_by_4:
        if year_divisible_by_100:
            if year_divisible_by_400:
                return True
            return False
        return True
    return False
