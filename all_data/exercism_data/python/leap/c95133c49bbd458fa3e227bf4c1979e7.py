def is_leap_year(number):
    if number % 400 == 0:
        return True
    if number % 100 == 0:
        return False
    if number % 4 == 0:
        return True
    return False
        
