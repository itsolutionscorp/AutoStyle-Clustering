def is_leap_year(year):
    divisible_by_4 = year % 4 == 0
    divisible_by_100 = year % 100 == 0
    divisible_by_400 = year % 400 == 0
    
    if divisible_by_4:
        if divisible_by_100 and not divisible_by_400:
            return False
        else:
            return True
    else:
        return False
