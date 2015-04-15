import datetime

def meetup_day(year, month, d, x):
  dow = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']

  if x == 'teenth':
    sd = 13
  else:
    sd = 1
  i =0 
  while i < 7:
    if datetime.date(year,month, sd+i).weekday() == dow.index(d):
      result = datetime.date(year,month,sd+i)

      if x == '1st':
        return result
      elif x == '2nd':
        return result + datetime.timedelta(days=7)
      elif x  == '3rd':
        return result + datetime.timedelta(days=14)
      elif x == '4th':
        return result + datetime.timedelta(days=21)
      elif x == 'last':
        import calendar
        ld = calendar.monthrange(year,month)[1]
        j = 0
        while j < 7:
          if datetime.date(year,month, ld-j).weekday() == dow.index(d):
            return datetime.date(year, month, ld-j)
          else:
            continue
          j += 1
      else:
        return result
    i += 1
