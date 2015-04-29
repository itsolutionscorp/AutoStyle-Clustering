'''
A leap-year occurs on all years that are evenly divisible by 4 but not
divisible by 100, and all years that are divisible by 400.
'''

def is_leap_year(y):
  return (y%4 == 0  and  y%100 != 0)  or  y%400 == 0
