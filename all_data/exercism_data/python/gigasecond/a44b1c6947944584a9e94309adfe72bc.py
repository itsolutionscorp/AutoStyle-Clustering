from datetime import datetime, timedelta

def add_gigasecond(dt):
  '''
  Returns a datetime that is one gigasecond (1e9) later than the passed datetime
  '''
  return dt + timedelta(seconds=1e9)
