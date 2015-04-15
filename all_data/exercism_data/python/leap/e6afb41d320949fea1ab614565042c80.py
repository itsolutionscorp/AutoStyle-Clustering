def is_leap_year(year):
    leap_year_conditions = [
        lambda y: y % 400 == 0,
        lambda y: y % 4 == 0 and y % 100 > 0
    ]
    return any(check(year) for check in leap_year_conditions)
