# -*- coding: utf-8 -*-

def is_leap_year(year):

  try:
    int(year)
  except ValueError:
    return False

  if year % 4 == 0:

    if year % 400 == 0:
      return True
    elif year % 100 == 0:
      return False
    else:
      return True

  else:
    return False
