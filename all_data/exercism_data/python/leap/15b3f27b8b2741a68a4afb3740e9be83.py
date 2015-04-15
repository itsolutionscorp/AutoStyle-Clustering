def is_divisible_by(amount, by):
  return amount % by == 0

def is_leap_year(year):
  if is_divisible_by(year, 400):
    return True
  elif is_divisible_by(year, 100):
    return False
  
  return is_divisible_by(year, 4)
