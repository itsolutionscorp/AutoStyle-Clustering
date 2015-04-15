def is_leap_year(year):
  if (year % 4 == 0):
    if not(year % 100 == 0) or (year % 400 == 0):
      return True
    else:
      return False
  else:
    return False
