from datetime import date
from datetime import timedelta

gigasesecond = timedelta(seconds=1000000000)

def add_gigasecond(date):
  return date + gigasesecond
