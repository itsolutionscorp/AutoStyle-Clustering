def is_leap_year(year):
    divisible_by_4 = year % 4 == 0
    divisible_by_100 = year % 100 == 0
    divisible_by_400 = year % 400 == 0
    
    is_leap = (divisible_by_4 and not divisible_by_100) or \
            (divisible_by_4 and divisible_by_400)
    
    return is_leap
