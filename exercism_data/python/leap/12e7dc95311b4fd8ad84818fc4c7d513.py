# -*- coding: utf-8 -*-

def is_leap_year(year):

  try:
    int(year)
  except ValueError:
    return False

  if year % 4 == 0:

    if year % 100 == 0:
      
      if year % 400 == 0:
        return True
      else:
        return False

    return True

  else:
    return False
