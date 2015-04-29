from datetime import date, timedelta 

def meetup_day(year, month, weekday_name, happens):
  weekday_number = __to_weekday_number__(weekday_name) 

  if happens == '1st':
    return __first__(year, month, weekday_number)
  elif happens == 'teenth': 
    return __teenth__(year, month, weekday_number)
  elif happens == 'last':
    return __last__(year, month, weekday_number)
  else: 
    first_weekday = __first__(year, month, weekday_number)
    weeks = __ordinal_to_number__(happens) - 1 
    days = weeks * 7
    day = first_weekday.day + days 
    return date(year, month, day)

def __to_weekday_number__(weekday_name): 
  weekday_numbers = __weekday_numbers__()

  if weekday_numbers.has_key(weekday_name): 
    return weekday_numbers[weekday_name]

def __weekday_numbers__():
  return {
    'Monday': 0,
    'Tuesday': 1,
    'Wednesday': 2,
    'Thursday': 3,
    'Friday': 4,
    'Saturday': 5,
    'Sunday': 6, }

def __first__(year, month, weekday_number):
  first_week = range(1,8)
  for day in first_week:
    meetup_date = date(year, month, day)
    if meetup_date.weekday() == weekday_number: 
      return meetup_date 

def __teenth__(year, month, weekday_number):
  teens = range(13,20)

  for teen in teens:
    meetup_date = date(year,month,teen)
    if meetup_date.weekday() == weekday_number:
      return meetup_date

def __ordinal_to_number__(simple_ordinal): 
  return int(simple_ordinal[0])

def __last__(year, month, weekday_number):
  last_week = range(7)
  last_day = date(year, month+1, 1) - timedelta(days=1)
  day = last_day.day 
  for days in last_week:
    meetup_date = date(year, month, day-days)
    if meetup_date.weekday() == weekday_number: 
      return meetup_date
