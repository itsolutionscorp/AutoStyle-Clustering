import time
from datetime import date, timedelta
def add_gigasecond(d):
  return d + timedelta(seconds=10**9)


add_gigasecond(date(1977, 6, 13))
