from datetime import timedelta

gigasecond_in_days = 10**9 / 60 / 60 / 24

def add_gigasecond(d):
  return d + timedelta(days=gigasecond_in_days)
