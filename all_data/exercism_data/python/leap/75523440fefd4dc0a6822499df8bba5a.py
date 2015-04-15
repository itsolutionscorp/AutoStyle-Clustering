import calendar

# def is_leap_year(year):
#     return bool(((not year % 4) and (year % 100)) or (not year % 400))

def is_divisible_by(a,b):
    return a % b == 0

def is_leap_year(year):
    by_four = is_divisible_by(year, 4)
    by_hundred = is_divisible_by(year, 100)
    by_four_hundred = is_divisible_by(year, 400)

    if by_four and not by_hundred:
        return True
    elif by_four_hundred:
        return True
    else:
        return False
