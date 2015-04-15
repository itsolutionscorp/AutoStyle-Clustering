#!/usr/bin/env python

def is_leap_year(queryYear):
  if queryYear % 4 != 0:
    return False
  elif queryYear % 100 == 0 and queryYear % 400 != 0:
    return False
  else:
    return True
