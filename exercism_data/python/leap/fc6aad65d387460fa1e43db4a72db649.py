def is_leap_year(year): 
    "tell whether or not a year is a leap year"
    if year%100==0 and year%400!=0:
        return False
    if year%4==0:
        return True
    else:
        return False
