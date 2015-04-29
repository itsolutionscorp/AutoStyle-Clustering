def is_leap_year(date):
# A year is a leap year if...
#	It's divisable by 4
#	Not divisable by 100
#	Unless it's also divisable by 400

  if(divisible_by_400(date)):
    return True;
  elif(divisible_by_100(date)):
    return False;
  elif(divisible_by_4(date)):
    return True;

  return False;

def divisible_by_400(date):
  if(date % 400):
    return False;
  else:
    return True;

def divisible_by_100(date):
  if(date % 100):
    return False;
  else:
    return True;

def divisible_by_4(date):
  if(date % 4):
    return False;
  else:
    return True;
