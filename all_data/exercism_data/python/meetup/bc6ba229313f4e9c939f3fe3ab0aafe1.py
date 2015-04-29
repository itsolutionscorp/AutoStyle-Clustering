import datetime
import calendar

def meetup_day(y,m,d,n):
  """ given year month day num, find actual date """

  # handle, teenth, 1st, 2nd, 3rd, 4th, last
  weekmap = {
    'Monday'  : 0,
    'Tuesday' : 1,
    'Wednesday' : 2,
    'Thursday' : 3,
    'Friday' : 4,
    'Saturday' : 5,
    'Sunday' : 6
  }
  # given n starting date
  startmap = {
    'teenth' : 13,
    '1st' : 1,
    '2nd' : 8,
    '3rd' : 15,
    '4th' : 22,
    'last' : calendar.monthrange(y,m)[1] - 6
  }

  for i in xrange(7):
    day = datetime.date(y,m,startmap[n]+i).weekday()
    if day == weekmap[d]:
      return datetime.date(y,m, startmap[n]+i)
