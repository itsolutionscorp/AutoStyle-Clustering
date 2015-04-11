import datetime

def add_gigasecond(d):

  dt = datetime.datetime(d.year, d.month, d.day)
 
  return datetime.date.fromtimestamp((dt-datetime.datetime(1970,1,1)).total_seconds() + 10**9)
