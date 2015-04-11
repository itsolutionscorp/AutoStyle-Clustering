import calendar
from datetime import date

def meetup_day(year, month, weeks, times):
  week = weeks.upper()
  time1 = 0
  count = 0

  day = {'MONDAY': 0, 'TUESDAY':1, 'WEDNESDAY':2, 'THURSDAY':3, 'FRIDAY':4,
         'SATURDAY':5, 'SUNDAY':6}
  time = {'1st':0, '2nd':1, '3rd':2, '4th':3, 'last':4, 'teenth':0}

  x = calendar.monthcalendar(year, month)

  if x[time[times]][day[week]] == 0 or x[0][day[week]] == 0:
    time1 = time[times] + 1
  else:
    time1 = time[times]

  if times == 'teenth':
    for i in x:
      for j in range(0, 6):
        if i[j] == 16:
          time1 = count

      count += 1

  return date(year, month, x[time1][day[week]])
