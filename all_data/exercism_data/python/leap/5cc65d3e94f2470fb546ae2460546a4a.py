def is_leap_year(year):
    if divisible(year, 100) and not divisible(year, 400):
        return False
    elif divisible(year, 4):
        return True
    return False

def divisible(a, b):
  return a % b == 0
