from datetime import datetime 
import calendar 

def add_gigasecond(datetime_obj):
  unix_time = __to_unix_time__(datetime_obj)
  later_time = unix_time + __a_billion__() 
  return datetime.utcfromtimestamp(later_time)

def __to_unix_time__(datetime_obj): 
  unix_epoch = datetime(1970, 1,1)
  difference = (datetime_obj - unix_epoch)
  return difference.total_seconds()

def __a_billion__():
  return 10**9
