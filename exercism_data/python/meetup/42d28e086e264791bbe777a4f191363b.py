import calendar
import datetime 

def meetup_day(year, month, day, ord):
  days = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"]
  dayidx = days.index(day.upper())
  if ord == "last":
    cal = calendar.Calendar(0)
    mon = cal.monthdatescalendar(year,month)
    lastweek = mon[-1]
    return lastweek[dayidx]

  elif ord == "teenth":
    return
  else:
    cal = calendar.Calendar(dayidx)
    mon = cal.monthdatescalendar(year,month)
    nth = int(ord[0])
    return mon[nth][0]

  # td = datetime.timedelta(days = 1)
  # firstday = calendar.monthrange(year,month)[0]
  # calendar.setfirstweekday(dayidx)
  # dtmtemp = 
  # while 

#  nth = int(ord[0])

#  return calendar.Calendar(dayidx).monthdatescalendar(year,month)[nth][0]
