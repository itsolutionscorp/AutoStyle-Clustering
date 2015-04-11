def is_leap_year(year):
    leapyear = False
    if year % 4 == 0:
        leapyear=True
    if year % 100 == 0:
        leapyear = False
    if year % 400 == 0:
        leapyear = True
    
    return leapyear
