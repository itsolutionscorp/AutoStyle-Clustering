def is_leap_year(year):
    return not bool(
        (year % 4) or not(year % 100) and (year % 400)
    )
