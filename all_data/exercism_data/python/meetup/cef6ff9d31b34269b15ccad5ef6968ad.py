from datetime import date, timedelta
from calendar import Calendar, day_name
def meetup_day(year, month, day_of_week, classifier):
  fns = {
    'first' : lambda x,_: x == 1,
    '1st'   : lambda x,_: x == 1,
    '2nd'   : lambda x,_: x == 2,
    '3rd'   : lambda x,_: x == 3,
    '4th'   : lambda x,_: x == 4,
    '5th'   : lambda x,_: x == 5,
    'teenth': lambda _,y: 13 <= y <= 19
    }
  
  cal = Calendar()
  month_day_names = [(day_num, day_name[weekday]) for day_num, weekday in cal.itermonthdays2(year, month) if 1 <= day_num <= 31]
  
  if classifier == 'last':
    month_day_names = reversed(month_day_names)
    classifier = 'first'
  elif classifier.endswith('teenth'):
    classifier = 'teenth'

  counter = 0
  for day_num, weekday in month_day_names:
    if weekday == day_of_week:
      counter += 1
      if fns[classifier](counter, day_num):
        return date(year, month, day_num)
  raise Exception('meetup day not found!')
