#Not sure if using timedelta is "legal" within the parameters of the assignment
from datetime import timedelta


def add_gigasecond(birthdate):
  return birthdate + timedelta(seconds = 10**9)  
  
  
