def is_leap_year(y):
    every4   = y % 4 == 0
    every100 = y % 100 == 0
    every400 = y % 400 == 0
    return every400 or every4 and not every100
