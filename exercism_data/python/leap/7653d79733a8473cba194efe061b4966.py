def divisible(x, y): #test if y divides x
    return x % y == 0

def is_leap_year(year):
    
    if not divisible(year, 4): #common years
        return False
    if not divisible(year, 100): #leap years bar special cases
        return True
    if divisible(year, 400): #special cases
        return True
    else:
        return False
