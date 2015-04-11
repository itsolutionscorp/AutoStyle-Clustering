from datetime import timedelta

def add_gigasecond(dob):
  return dob + timedelta(seconds = 1e9)
