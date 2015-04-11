def is_leap_year(year):

    if year % 100 == 0:
        return not (year % 400)

    return not (year % 4)
