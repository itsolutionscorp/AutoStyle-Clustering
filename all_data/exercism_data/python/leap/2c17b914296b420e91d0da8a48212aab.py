def is_leap_year(year):
    return is_exceptional_century(year) or (is_divisible_by_four(year) and not is_century(year))


def is_divisible_by_four(year):
    return year % 4 == 0


def is_century(year):
    return year % 100 == 0


def is_exceptional_century(year):
    return year % 400 == 0
