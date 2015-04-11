#Not sure if using timedelta is "legal" within the parameters of the assignment
from datetime import timedelta


def add_gigasecond(birthdate):
    
  SECONDS_PER_DAY = 24*3600
  deltdays = 1000000000 /SECONDS_PER_DAY
  td = timedelta(days = deltdays)
  
  return birthdate + td  
  
  
