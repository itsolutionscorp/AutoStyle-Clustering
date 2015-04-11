def is_leap_year(year):
    
    is_div_by_4 = year % 4 == 0
    is_div_by_100 = year % 100 == 0
    is_div_by_400 = year % 400 == 0

    not_a_leap_year = is_div_by_100 and not is_div_by_400


    if is_div_by_4 and not_a_leap_year: return False
        
    if is_div_by_4: return True
        
    return False 
