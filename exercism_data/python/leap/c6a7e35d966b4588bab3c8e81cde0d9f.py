def is_leap_year(input):
    if input % 100 == 0 and not input % 400 == 0: return False 
    if input % 4 == 0: return True 
    return False
