def is_leap_year(year):
    every4 = year % 4 == 0
    every100 = year % 100 == 0
    every400 = year % 400 == 0
    if every400:
        return True
    if every100:
        return False
    if every4:
        return True
    return False
