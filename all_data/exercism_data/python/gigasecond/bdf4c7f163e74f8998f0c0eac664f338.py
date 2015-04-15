from datetime import timedelta

def add_gigasecond(startDate):
  return startDate + timedelta(seconds=1e9)
