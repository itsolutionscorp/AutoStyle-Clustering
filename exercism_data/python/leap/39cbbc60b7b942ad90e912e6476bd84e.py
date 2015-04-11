def is_leap_year(year):
  if bool(year % 4):
    return False
  return bool(year % 100) or not bool(year % 400)
