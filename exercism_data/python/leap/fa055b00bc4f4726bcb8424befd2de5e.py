def is_leap_year(year):
  
  is_exception = year % 100 == 0 and year % 400 !=0
  
  return year % 4 == 0 and not is_exception
