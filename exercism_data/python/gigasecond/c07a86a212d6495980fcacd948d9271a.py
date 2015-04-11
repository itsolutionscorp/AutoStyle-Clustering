from datetime import date
from datetime import timedelta
def add_gigasecond(dt):
   dys = 10**9/60/60/24
   return dt + timedelta(days=dys)
