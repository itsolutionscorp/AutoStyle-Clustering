def is_leap_year(year):
    """plain
    on every year that is evenly divisible by 4
      except every year that is evenly divisible by 100
      unless the year is also evenly divisible by 400
    For example, 1997 is not a leap year, but 1996 is.  1900 is not a leap
    year, but 2000 is.
    """
    isleap = False
    if year % 4 == 0:
        isleap = True
        if year % 100 == 0:
            isleap = False
            if year % 400 == 0:
                isleap = True
    return isleap
