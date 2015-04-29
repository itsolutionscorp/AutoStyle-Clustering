def is_leap_year(year):
    """on every year that is evenly divisible by 4
    except every year that is evenly divisible by 100
    unless the year is also evenly divisible by 400
    """

    if is_divisible(year, 400):
        return True

    elif is_divisible(year, 100):
        return False

    elif is_divisible(year, 4):
        return True

    else:
        return False


def is_divisible(year, divisor):
    return year % divisor == 0
