# File for the leap_year exercise on exercism (2)

def is_leap_year(year):
    # Divisible by 4 AND (not divisible by 100 OR divisible by 400)
    if year%4 == 0 and (not year%100 == 0 or (year%400 == 0)):
        return True
    
    return False
