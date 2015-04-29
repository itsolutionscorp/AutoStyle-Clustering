'''
A leap-year occurs on every year that is evenly divisible by 4,
except every year that is evenly divisible by 100,
unless the year is also evenly divisible by 400.
'''

def is_leap_year(y):
  return y%4 == 0  and  (y%100 != 0  or  y%400 == 0)
