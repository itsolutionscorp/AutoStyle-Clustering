def is_leap_year(year):
  byfour = not bool(year % 4)
  by100 = not (year % 100)
  by400 = not (year % 400)
  
  if(byfour and not by100 or by400):
    return True
  return False
