def divisible_by_4(year):
    return year%4 == 0

def not_divisible_by_100(year):
    return year%100 != 0

def divisible_by_400(year):
    return year%400 == 0


def is_leap_year(year):
    if (divisible_by_4(year) and (not_divisible_by_100(year))) or (divisible_by_400(year)):
        return True
    return False
