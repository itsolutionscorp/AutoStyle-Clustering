"""    on every year that is evenly divisible by 4
  except every year that is evenly divisible by 100
    unless the year is also evenly divisible by 400
"""


def is_leap_year(testyear):
    if testyear % 400 == 0:
        return True
    if testyear % 100 == 0:
        return False
    if testyear % 4 == 0:
        return True
    return False
