def is_leap_year(year):
    year = int(year)
    div_by_4 = year % 4 is 0
    div_by_100 = year % 100 is 0
    div_by_400 = year % 400 is 0
    return div_by_4 and (not div_by_100 or div_by_400)
