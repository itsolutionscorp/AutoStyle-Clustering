def is_leap_year(input) :
  if input%400 == 0 : 
    return True
  if input%100 == 0 :
    return False
  if input%4 == 0 :
    return True

  return False
