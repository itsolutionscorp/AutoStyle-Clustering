from datetime import timedelta

def add_gigasecond(date):

   d = timedelta(seconds=(10**9))
   return date + d
