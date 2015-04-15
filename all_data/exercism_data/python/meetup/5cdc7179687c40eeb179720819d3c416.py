from datetime import date
import calendar

def meetup_day(year, month, dow, ordinal):
  c = calendar.Calendar()
  day_num = __get_day_num(dow)
  # Meaty part - returns tuples of (day_int, dow_int) for every day in
  # the month, then grabs the tuples that are for the correct day of the
  # week (i.e. All Mondays)
  day_dayofweek = c.monthdays2calendar(year, month)
  day_int = [week[day_num] for week in day_dayofweek if week[day_num][0] != 0]
  print day_int
  order_num = __get_day_from_ordinal(ordinal, day_int)
  try:
    day = day_int[order_num][0]
  except:
    raise MeetupDayException("Bad Date")
  return date(year, month, day)

def __get_day_num(dow):
  weekdays = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3,
              'Friday': 4, 'Saturday': 5, 'Sunday': 6}
  return weekdays[dow]

def __get_day_from_ordinal(ordinal, day_int):
  '''Also handles "teenth"'''
  order = {'1st': 0, 'first': 0, '2nd': 1, '3rd': 2,
           '4th': 3, '5th': 4, 'last': -1}
  if ordinal == 'teenth':
    for i in range(len(day_int)):
      day_num = day_int[i][0]
      if 12 < day_num < 20:
        return i
  return order[ordinal]
print meetup_day(2013, 2, 'Saturday', 'first')


class MeetupDayException(Exception):
  def __init__(self, arg):
    self.msg = arg
