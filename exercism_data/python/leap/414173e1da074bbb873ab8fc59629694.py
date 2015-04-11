def is_leap_year(year):
  return not bool(year % 4) and not (not bool(year % 100) and bool(year % 400))
