import datetime
import calendar

def meetup_day(year, month, name, interval):

  teenths = [13, 14, 15, 16, 17, 18, 19]
  weekdays = {
    "Monday": 0,
    "Tuesday": 1,
    "Wednesday": 2,
    "Thursday": 3,
    "Friday": 4,
    "Saturday": 5,
    "Sunday": 6
  }

  numberoftimes = {
    "1st": 0,
    "2nd": 1,
    "3rd": 2,
    "4th": 3,
    "5th": 4
  }

  founddate = 1
  max_day = calendar.monthrange(year, month)

  if interval == "teenth":
    # teenth dayofweek
    for i in range(13,20):
      if calendar.weekday(year, month, i) == weekdays[name]:
        founddate = i
  elif interval == "last":
    # last in month
    for i in range(max_day[1], 1,-1):
      if calendar.weekday(year, month, i) == weekdays[name]:
        founddate = i
        break
  else:
    # 1st to 5th dayofweek
    # find first instance of that dayofweek in this month
    # iterate from first day of month to find the right day number
    for i in range(1,8):
      if calendar.weekday(year, month, i) == weekdays[name]:
        founddate = i
        # add 7 days until you hit the iteration that you're looking for
        founddate = founddate + (7 * numberoftimes[interval])
    # if adding 7 takes you past the end of the month, throw an exception? and break
    if founddate > max_day[1]:
      raise Exception

  return datetime.date(year, month, day=founddate)
