def is_leap_year(year):
    if divisible(year, 4):
        if  not divisible(year, 100):
            return True
        elif divisible(year, 400):
                return True
    else:
        return False

def divisible(a, b):
    if a % b == 0:
        return True
    else:
        return False
