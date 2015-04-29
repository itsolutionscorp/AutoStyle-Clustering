def is_leap_year(year):
    leap_year = False

    if year % 4 == 0:
        leap_year = True

    if year % 100 == 0:
        leap_year = False

    if year % 400 == 0:
        leap_year = True

    return leap_year
