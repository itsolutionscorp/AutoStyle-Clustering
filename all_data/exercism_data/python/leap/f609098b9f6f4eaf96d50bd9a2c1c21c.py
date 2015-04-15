def is_leap_year(year):
  if     year % 4:   return False
  if not year % 400: return True
  return year % 100
