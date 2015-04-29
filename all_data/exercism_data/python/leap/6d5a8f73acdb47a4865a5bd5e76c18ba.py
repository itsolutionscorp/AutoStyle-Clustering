def is_leap_year(year):
    if divisible_by(year, 400):
        return True
    if divisible_by(year, 100):
        return False
    return divisible_by(year, 4)

def divisible_by(value, factor):
    return value % factor == 0
