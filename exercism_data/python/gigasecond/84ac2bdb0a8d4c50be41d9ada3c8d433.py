from datetime import date, timedelta

def add_gigasecond(birthday):
  td = timedelta(seconds=10**9)
  return birthday + td
