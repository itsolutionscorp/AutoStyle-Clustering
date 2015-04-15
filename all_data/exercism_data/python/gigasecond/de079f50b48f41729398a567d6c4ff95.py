from datetime import timedelta

def add_gigasecond(dob):
  return dob + timedelta(seconds = int(1e9))
