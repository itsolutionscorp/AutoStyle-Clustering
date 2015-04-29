from datetime import timedelta
def add_gigasecond(date):
  date += timedelta(seconds = 1000000000)
  return date
