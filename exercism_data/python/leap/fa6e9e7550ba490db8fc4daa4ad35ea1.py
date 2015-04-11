def is_leap_year(year):
  if not year % 4 == 0:
    return False
  if year % 100 == 0:
    if not year % 400 == 0:
      return False
  return True
