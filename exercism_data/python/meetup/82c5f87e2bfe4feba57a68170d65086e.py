import calendar
import datetime

ORDINALS = { '1st': 0, '2nd': 1, '3rd': 2, '4th': 3, 'last': -1 }

WEEKDAYS = {
  'Monday': calendar.MONDAY,
  'Tuesday': calendar.TUESDAY,
  'Wednesday': calendar.WEDNESDAY,
  'Thursday': calendar.THURSDAY,
  'Friday': calendar.FRIDAY,
  'Saturday': calendar.SATURDAY,
  'Sunday': calendar.SUNDAY
}

def meetup_day(year, month, weekday, ordinal):
  dt = datetime.date(year,month,1)
  dow_lst = []

  # Get the first given weekday of the month (e.g., Monday)
  while dt.weekday() != WEEKDAYS[weekday]:
    dt = dt + datetime.timedelta(days=1)

  # Get each given day of the month (e.g., all of the Mondays)
  while dt.month == month:
    dow_lst.append(dt)

    # Short circuit if we can
    if ordinal == 'teenth' and dt.day > 12 and dt.day < 20:
      return dt

    dt = dt + datetime.timedelta(days=7)

  return dow_lst[ORDINALS[ordinal]]
