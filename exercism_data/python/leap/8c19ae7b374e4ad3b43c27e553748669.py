def is_leap_year(year):
    mod_4 = year % 4 == 0
    mod_100 = year % 100 == 0
    mod_400 = year % 400 == 0

    return mod_4 and (mod_400 or not mod_100)
