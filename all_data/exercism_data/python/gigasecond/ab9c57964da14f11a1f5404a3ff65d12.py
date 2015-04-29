import datetime

GIGASECOND_DAYS = (((1000000000 / 60) / 60) / 24)

def add_gigasecond(date):
  return date + datetime.timedelta(days = GIGASECOND_DAYS)
