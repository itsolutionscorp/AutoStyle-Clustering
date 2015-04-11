def is_leap_year(year):
  if not year % 400: return True
  if not year % 100: return False
  if not year % 4:   return True
  return False
