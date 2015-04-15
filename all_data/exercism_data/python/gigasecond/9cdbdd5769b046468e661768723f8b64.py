from datetime import timedelta
def add_gigasecond(birthday):
  d = 10**9/60/60/24
  return birthday + timedelta(days=d)
