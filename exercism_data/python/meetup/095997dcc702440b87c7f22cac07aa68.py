import sys
import datetime

def meetup_day(year, month, day, occ):

  week = {"Monday" : 0, "Tuesday": 1, "Wednesday": 2, "Thursday": 3, "Friday": 4, "Saturday": 5, "Sunday": 6}

  first = None
  for i in range(7):
    dt = datetime.date(year, month, i+1)
    if (dt.weekday() == week[day]):
      first = dt
      break
    i += 1

  if first == None:
    print "ERROR!"
    sys.exit(1) 

  if occ == "1st":
    return first
  elif occ == "2nd":
    return datetime.date(first.year, first.month, first.day + 7)
  elif occ == "3rd":
    return datetime.date(first.year, first.month, first.day + 14)
  elif occ == "4th":
    return datetime.date(first.year, first.month, first.day + 21)
  elif occ == "last":
    try:
      last_dt = datetime.date(first.year, first.month, first.day + 28)
    except ValueError:
      last_dt = datetime.date(first.year, first.month, first.day + 21)
    return last_dt
  elif occ == "teenth":
    if (first.day + 7 > 12):
      return datetime.date(first.year, first.month, first.day + 7)
    else:
      return datetime.date(first.year, first.month, first.day + 14)
  else:
    print "ERROR!"
    sys.exit(1)
