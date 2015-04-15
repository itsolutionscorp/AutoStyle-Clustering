from datetime import date
from calendar import monthrange

class MeetupDayException(Exception):
    pass

weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']

def meetup_day(year, month, dow, schedule):
  occurance = 0
  last_match = None

  for day in range(monthrange(year, month)[1]):
    check = date(year, month, day + 1)

    if check.weekday() == weekdays.index(dow):
      last_match = check
      occurance += 1

      if schedule == 'teenth' and check.day in range(13, 19):
        return check

      elif schedule.startswith(str(occurance)):
        return check

  if schedule == 'last':
    return last_match

  raise MeetupDayException("Invalid date")
