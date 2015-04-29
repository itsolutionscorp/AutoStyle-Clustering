def is_leap_year(date):
# A year is a leap year if...
#	It's divisable by 4
#	Not divisable by 100
#	Unless it's also divisable by 400

  if(not (date % 400)): # If the year is divisable by 400, then we're a leap year
    return True;
  elif(not (date % 100) ): # Years divisable by 100 are never leap years
    return False;
  elif(not (date % 4)): # But otherwise, if they're divisable by 4 they are leap years
    return True;
