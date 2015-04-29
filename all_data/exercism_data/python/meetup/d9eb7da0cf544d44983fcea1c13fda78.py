import calendar
import time
import datetime


def meetup_day(year, month, weekday, relative_description):
  first_of_month = datetime.date(year, month, 1)
  requested_weekday = time.strptime(weekday, "%A").tm_wday
  days_ahead = (requested_weekday - first_of_month.weekday()) % 7
  first_weekday_of_month = first_of_month + datetime.timedelta(days_ahead)
  return first_weekday_of_month + \
      relative_days_added(relative_description, first_weekday_of_month.day, calendar.monthrange(year, month)[1])


def relative_days_added(text, current_day, days_in_month):
  parsed_descriptor = [int(s) for s in text if s.isdigit()]
  add_days = 0
  if len(parsed_descriptor) > 0:
    add_days = (parsed_descriptor[0] - 1) * 7
  elif text == 'last':
    while current_day + add_days + 7 <= days_in_month:
      add_days += 7
  elif text == 'teenth':
    while True:
      add_days += 7
      if add_days + current_day >= 13:
        break
  return datetime.timedelta(add_days)
