import datetime

def add_gigasecond(birthday):
  solution = birthday

  giga = 10**9

  # 60 seconds in minute
  minutes = int(giga) / 60
  seconds = giga % 60

  # 60 minutes in hour
  hours = minutes / 60
  minutes = minutes % 60

  # 24 hours in day
  days = hours / 24
  hours = hours % 24
  
  # 365 days in year
  # years = days / 365
  # days = days % 365

  solution = birthday + datetime.timedelta(days=days, hours=hours, minutes=minutes, seconds=seconds)
  return solution
