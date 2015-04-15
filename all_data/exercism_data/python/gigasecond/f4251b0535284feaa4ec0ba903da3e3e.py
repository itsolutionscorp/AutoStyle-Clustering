from datetime import timedelta

def add_gigasecond(start_date):

  GIGASECOND = 10**9

  return start_date + timedelta(seconds=GIGASECOND)
