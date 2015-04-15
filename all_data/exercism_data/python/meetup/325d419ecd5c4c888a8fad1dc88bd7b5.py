from datetime import date
from calendar import Calendar, day_name
from functools import partial

def meetup_day(year, month, day_of_week, classifier):
  c_idx = {'first' : 0, '1st': 0, '2nd': 1, '3rd': 2, '4th': 3, '5th': 4, 'last': -1}
  
  cal = Calendar()
  # only get the days of the month which correspond to day_of_week
  # itermonthdates returns full weeks either side, so we filter on the month
  candidates = [dt.day for dt in cal.itermonthdates(year, month) if day_name[dt.weekday()] == day_of_week and dt.month == month]

  date_fn = partial(date, year, month)
  if classifier.endswith('teenth'):
    return date_fn( next(d for d in candidates if 13 <= d <= 19) )
  elif c_idx[classifier] < len(candidates):
    return date_fn( candidates[c_idx[classifier]] ) 
  raise Exception('meetup day not found!')
