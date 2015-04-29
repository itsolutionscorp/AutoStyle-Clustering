from datetime import date,timedelta

Gs = timedelta(seconds = 1000000000)
      
def add_gigasecond(birthdate):
  Gsdate = birthdate + Gs
  return Gsdate
