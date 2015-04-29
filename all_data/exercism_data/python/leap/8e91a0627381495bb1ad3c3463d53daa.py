def is_leap_year(year):
    """ Returns True if year is a leap year, else returns False
    """
    fourth_century = year % 400 == 0
    normal_century = year % 100 == 0
    normal_leap_year = year % 4 == 0

    return fourth_century or (normal_leap_year and not normal_century)
