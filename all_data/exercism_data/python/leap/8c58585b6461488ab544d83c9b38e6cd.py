def is_leap_year(year):
    """
    Returs if year is a leap year, which is
    every year that is evenly divisible by 4
    except every year that is evenly divisible by 100
    unless the year is also evenly divisible by 400
    """
    by4 = (year % 4) == 0
    by100 = (year % 100) == 0
    by400 = (year % 400) == 0
    return by4 and (not by100 or by400)
