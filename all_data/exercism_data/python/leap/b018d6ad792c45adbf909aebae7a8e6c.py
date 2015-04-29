def is_leap_year(year):
    year_is_evenly_divisible_by = by = lambda x: year % x == 0
    #English, m**********r! Do you speak it?! :)
    if year_is_evenly_divisible_by(400) or (by(4) and not by(100)): 
        return True
    else:
        return False
