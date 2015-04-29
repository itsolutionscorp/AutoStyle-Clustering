import datetime
import calendar

def meetup_day (year, month, dayname, order) :
  dayfromname = {"Monday":0, "Tuesday":1, "Wednesday":2, "Thursday":3, "Friday":4, "Saturday":5, "Sunday":6}
  lastseven = calendar.monthrange(year, month)[1] - 6
  newdaymap = {"1st":1, "2nd":8, "3rd":14, "4th":21, "teenth": 13, "last": lastseven}
  newdate = datetime.date(year, month, newdaymap[order])

  return (newdate + datetime.timedelta( (7 + dayfromname[dayname] - newdate.weekday())%7 ))
