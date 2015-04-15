def is_leap_year(year):
    return True if (four_hundred_year_leap_years(year) or
                   leap_years_excluding_centuries(year)) else False


def four_hundred_year_leap_years(year):
    return True if not (year % 400) else False


def leap_years_excluding_centuries(year):
    return True if not (year % 4) and (year % 100) else False
