def is_leap_year(year):
  if not(year % 4 == 0):
    return False

  elif not(year % 100 == 0):
    return True

  elif year % 400 == 0:
    return True

  return False
