def is_leap_year(year):
    if can_divide_by_4_not_by_100(year) or can_divide_by_400(year):
        return True

    return False


def can_divide_by_400(year):
    return year % 400 == 0

def can_divide_by_4_not_by_100(year):
    return year % 4 == 0 and year % 100 != 0