def is_multiple(number, divisor):
    return number % divisor == 0

def is_leap_year(year):
    if is_multiple(year, 400):
        return True
    if is_multiple(year, 100):
        return False
    if is_multiple(year, 4):
        return True
    return False
