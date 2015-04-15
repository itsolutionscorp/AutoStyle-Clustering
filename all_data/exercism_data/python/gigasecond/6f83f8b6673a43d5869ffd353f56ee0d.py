from datetime import timedelta
GIGASECINDAYS = 11574
def add_gigasecond(aDate):
   return( aDate + timedelta(GIGASECINDAYS) )
