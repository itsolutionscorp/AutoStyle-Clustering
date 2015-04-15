import calendar
import datetime

WEEKDAYS = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']

NTH = {
  'first': 0,
  '1st': 0,
  'second': 1,
  '2nd': 1,
  'third': 2,
  '3rd': 2,
  'fourth': 3,
  '4th': 3,
  'fifth': 4,
  '5th': 4,
}

def meetup_day(year, month, weekday_name, weekday_id):
  weekday_id = weekday_id.lower()
  # This returns a list of weeks, each of which is a list of (day_of_month, weekday_index) tuples. 
  # Sometimes day_of_month==0, meaning this is actually a day from the previous month.
  cmonth = calendar.Calendar().monthdays2calendar(year, month)
  weekday_index = WEEKDAYS.index(weekday_name)
  
  # Case 1: A specific ordinal
  if weekday_id in NTH:
    day = nth_day(cmonth, weekday_index, NTH[weekday_id])
  
  # Case 2: Last
  elif weekday_id == 'last':
    day = last_day(cmonth, weekday_index)
    
  # Case 3: Teenth
  elif weekday_id == 'teenth':
    day = teenth_day(cmonth, weekday_index)
    
  else:
    raise ValueError("Unrecognized weekday identifier")
    
  return datetime.date(year=year, month=month, day=day)
    
# The below helper functions all take in a month in the format returned by Calendar.monthdays2calendar, 
# and return a day of the month.    
    
def teenth_day(month, weekday_index):
  """Return the day of the given month that is the correct weekday and is a 'teen'.
  There will always be precisely one."""
  for week in month:
    for (day_of_month, weekday) in week:
      if 13 <= day_of_month <= 19 and weekday == weekday_index:
        return day_of_month
    
def last_day(month, weekday_index):
  day = None
  for week in month:
    for (day_of_month, weekday) in week:
      if weekday_index == weekday and day_of_month != 0:
        day = day_of_month
  assert day is not None
  return day
  
# n starts from 0
def nth_day(month, weekday_index, n):
  for week in month:
    for (day_of_month, weekday) in week:
      if weekday_index == weekday and day_of_month != 0:
        if n == 0:
          return day_of_month
        n -= 1
