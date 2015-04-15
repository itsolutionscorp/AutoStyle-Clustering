def is_leap_year(input):
    normal = input % 4 == 0
    century_ok = True
    if input % 100 == 0 and not input % 400 == 0:
        century_ok = False
    
    
    return normal and century_ok
