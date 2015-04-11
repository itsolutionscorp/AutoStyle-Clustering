import calendar
import datetime
QUALIFIERS = {'1st':0, '2nd':1, '3rd':2, '4th':3 }

def getLastMeetupDay(year, month, day):
  c = calendar.monthcalendar(year, month)
  i = 4
  if not c[i][day]:
    i -= 1
  return datetime.date(year, month, c[i][day])

def getTeenthMeetupDay(year, month, day):
  for d in range(13, 13+7):
    if day == calendar.weekday(year, month, d):
      return datetime.date(year, month, d)
  return None

def getNthMeetupDay(year, month, day, qualifier):
  """ qualifier = 1st, 2nd, 3rd, 4th """
  c = calendar.monthcalendar(year, month)
  i = QUALIFIERS[qualifier]
  if not c[0][day]:
    i += 1
  return datetime.date(year, month, c[i][day])


def meetup_day(year, month, day, qualifier):
  day = getattr(calendar, day.upper())
  if qualifier in ["last", "5th"]:
    return getLastMeetupDay(year, month, day)
  elif qualifier == "teenth":
    return getTeenthMeetupDay(year, month, day)
  elif qualifier in QUALIFIERS:
    return getNthMeetupDay(year, month, day, qualifier)
  else:
    raise ValueError("Invalid input")
