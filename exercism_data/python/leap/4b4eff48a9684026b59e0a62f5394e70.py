from operator import mod


def is_leap_year(year):
    """TODO: Docstring for is_leap_year.

    :year: TODO
    :returns: TODO

    """
    return not mod(year, 4) and not (not mod(year, 100) and mod(year, 400))
