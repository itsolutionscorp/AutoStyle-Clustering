__author__ = 'jakesawyer'

def is_leap_year(year):
  if year % 4 == 0:
    if year % 100 == 0 and year % 400 != 0:
      return False
    return True