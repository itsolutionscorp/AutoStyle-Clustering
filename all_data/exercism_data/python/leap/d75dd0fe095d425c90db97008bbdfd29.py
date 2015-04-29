def is_leap_year(year):
    """
        on every year that is evenly divisible by 4
          except every year that is evenly divisible by 100
            unless the year is also evenly divisible by 400
    """
    is_divisible_by_4 = (year % 4 == 0)
    not_divisible_by_100 = (year % 100 != 0)
    divisible_by_400 = (year % 400 == 0)
    
    if is_divisible_by_4 and not_divisible_by_100 or divisible_by_400:
        return True
    else:
        return False
