from calendar import Calendar, day_name
from datetime import date

def meetup_day(year, month, day, recurance):
  cal = Calendar()

  days = list(day_name)
  days_of_week = {days[i]:i for i in range(0,len(days))}
  meetup_dow = days_of_week[day]
  days_in_month = cal.itermonthdays2(year, month)

  count = 0
  meetup_date = None
  for day in days_in_month:
    if(day[0] != 0 and day[1] == meetup_dow):
      count = count + 1
      if(recurance is 'teenth' and day[0] in range(13,20)):
        return date(year, month, day[0])
      elif(recurance is '1st' and count == 1):
        return date(year, month, day[0])
      elif(recurance is '2nd' and count == 2):
        return date(year, month, day[0])
      elif(recurance is '3rd' and count == 3):
        return date(year, month, day[0])
      elif(recurance is '4th' and count == 4):
        return date(year, month, day[0])
      else:
        meetup_date = date(year, month, day[0])

  return meetup_date
