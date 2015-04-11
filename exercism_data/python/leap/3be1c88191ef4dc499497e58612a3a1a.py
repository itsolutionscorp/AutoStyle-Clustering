def is_leap_year(year):
  if year % 4 == 0:
    return year % 400 == 0 if year % 100 == 0 else True
  return False
