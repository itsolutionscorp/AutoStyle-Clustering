def is_leap_year(year):
    """ Returns True if year is a leap year, else returns False
    """
    evenly_divisable = lambda x, y: x % y == 0
    fourth_century = evenly_divisable(year, 400)
    normal_century = evenly_divisable(year, 100)
    normal_leap_year = evenly_divisable(year, 4)

    return fourth_century \
        or normal_leap_year and not normal_century
