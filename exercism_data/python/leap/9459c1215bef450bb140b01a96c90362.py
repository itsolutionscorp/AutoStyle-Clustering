#leap year is divisible by 4, by 400 and not by 100

def is_leap_year(year):
    return( not(year%4 or (not(year%100) and year%400) ))
    
    
