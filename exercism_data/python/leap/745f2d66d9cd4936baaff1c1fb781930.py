def is_leap_year(year):
  byfour = year % 4
  by100 = year % 100
  by400 = year % 400
  
  if(byfour and not by100 or by400):
    return true
  return false
