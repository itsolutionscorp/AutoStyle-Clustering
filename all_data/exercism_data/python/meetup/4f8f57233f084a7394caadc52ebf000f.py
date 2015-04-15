from datetime import date
import calendar

class MeetupDayException(Exception):
  pass

def meetup_day(year,month,weekday_name,ordinal_name):
  
  # Get list of weekdays (Jan 1, 2001 was a Monday)
  weekday_names = [date(2001, 1, i).strftime('%A') for i in range(1,8)]
  try:
    target_weekday = weekday_names.index(weekday_name) # 0-6
  except ValueError:
    raise MeetupDayException

  ordinals = ['teenth','1st','2nd','3rd','4th','5th','last']
  try:
    target_count = ordinals.index(ordinal_name)
  except ValueError:
    raise MeetupDayException

  # The days 13-19 are 'teenth'-days.
  teenth_days = set([i for i in range(13,20)])

  # Get starting weekday and number of days in month
  base_weekday, num_days_in_month = calendar.monthrange(year,month)

  # Set initial day value
  steps_to_target_weekday = ( (7+target_weekday) - base_weekday ) % 7
  day = 1 + steps_to_target_weekday

  # Increment day value as appropriate
  if ordinal_name == 'teenth':
    day += 7
    if day < 13:
      day += 7
  elif 1 <= target_count <= 5: # 1st to 5th
    order_count = 1
    while order_count != target_count:
      day += 7
      order_count += 1
      if day > num_days_in_month:
        raise MeetupDayException
  else: # last
    while day + 7 <= num_days_in_month:
      day += 7

  return date(year,month,day)
