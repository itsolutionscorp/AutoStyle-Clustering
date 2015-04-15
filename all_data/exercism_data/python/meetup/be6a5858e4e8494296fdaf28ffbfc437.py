import calendar
import datetime

def meetup_day(year, month, day, time):
  monthrange=calendar.monthrange(year, month)[1]
  d=datetime.datetime(year, month, 1)
  daymap={6:'Sunday', 0:'Monday', 1:'Tuesday', 2:'Wednesday', 3:'Thursday', 4:'Friday', 5:'Saturday'}
  i=1
  j=monthrange
  count=1

  if(time.lower()=='last'):
    d=datetime.datetime(year, month, monthrange)
    while j >= 1:
      dayofweek=d.weekday()
      if(daymap[dayofweek].lower()==day.lower()):
        return d.date()
      d-=datetime.timedelta(days=1)
      j-=1

  while i <= monthrange:
    dayofweek=d.weekday()
    if(daymap[dayofweek].lower()==day.lower()):
      if(count==1 and time.lower()=='1st'):
        return d.date()
      elif(count==2 and time.lower()=='2nd'):
        return d.date()
      elif(count==3 and time.lower()=='3rd'):
        return d.date()
      elif(count==4 and time.lower()=='4th'):
        return d.date()
      elif(time.lower()=='teenth' and d.day > 12 and d.day < 20):
        return d.date()
      count+=1
    d+=datetime.timedelta(days=1)
    i+=1

#print(meetup_day(2014, 10, 'Monday', 'teenth'))
