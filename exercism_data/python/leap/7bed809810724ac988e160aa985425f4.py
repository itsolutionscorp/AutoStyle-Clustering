def is_leap_year(year):

    """
    on every year that is evenly divisible by 4
        except every year that is evenly divisible by 100
            unless the year is also evenly divisible by 400
    """
    if year % 4 == 0:
        leap = True
        if year % 100 == 0:
            leap = False
            if year % 400 == 0:
                leap = True
    else:
        leap = False
    return leap