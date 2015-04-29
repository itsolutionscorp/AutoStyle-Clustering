def is_leap_year(yr):
  if(yr%4 != 0):
    return False
  else:
    if(yr%100 !=0):
      return True
    else:
      if(yr%400 != 0):
        return False
      else:
        return True
      
