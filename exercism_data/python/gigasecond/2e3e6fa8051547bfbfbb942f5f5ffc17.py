from datetime import timedelta
def add_gigasecond(date):
  date += timedelta(seconds = 10**9)
  return date
