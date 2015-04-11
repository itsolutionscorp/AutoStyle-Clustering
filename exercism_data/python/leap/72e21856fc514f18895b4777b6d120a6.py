def is_leap_year(year):
  remainder = year % 400
  if not remainder:
    return True
  else:
    if not (year % 100):
      return False
    if (year % 4) == 0:
      return True
  return False
