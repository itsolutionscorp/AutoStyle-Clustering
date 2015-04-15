from datetime import timedelta

def add_gigasecond(start_date):

  GIGASECOND = 10**9
  SECONDS_PER_DAY = 86400
  DAYS_IN_A_GIGASECOND = GIGASECOND / SECONDS_PER_DAY

  return start_date + timedelta(DAYS_IN_A_GIGASECOND)
