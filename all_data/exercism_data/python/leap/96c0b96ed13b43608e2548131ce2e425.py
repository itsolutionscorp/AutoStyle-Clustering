def is_leap_year(year):
    """
    Could maybe name the variables better.
    """
    by_four = year % 4 == 0
    by_hundred = year % 100 == 0
    by_four_hundred = year % 400 == 0
    
    return by_four_hundred or (by_four and not by_hundred)
