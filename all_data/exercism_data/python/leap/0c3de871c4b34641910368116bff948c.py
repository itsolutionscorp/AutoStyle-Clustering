def is_leap_year(year):

    validate(year)

    if year % 400 == 0:
        return True
    if year % 100 == 0:
        return False
    if year % 4 == 0:
        return True
    return False


def validate(year):
    if type(year) != int:
        raise TypeError("year must be an integer")
    if year < 0:
        raise ValueError("year must be non-negative")
