import datetime

def weekdaytoi(s):
  week = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday',\
  'saturday', 'sunday']
  return week.index(s.lower())

def buildCalendar(date):
  calendarL = []
  month = date.month
  #date = datetime.date(year, month, 1)
  oneDay = datetime.timedelta(days=1)

  while month == date.month:
    calendarL.append(date.weekday())
    date = date + oneDay

  return calendarL

def meetup_day(year, month, weekday, cardinal):
  # A `teenth` is a ordinal date from '13' to '19'.
  # Possible values for orderS are 'teenth', '1st', '2nd', '3rd', '4th',
  # and 'last'.
  #
  # date.weekday():
  # 0 = Monday .. 6 = Sunday

  date = datetime.date(year, month, 1)
  calendar = buildCalendar(date)
  weekday = weekdaytoi(weekday)

  if cardinal == 'teenth':
    start = 12
    end   = 19
    instance = 1
  elif cardinal == 'last':
    start = len(calendar) - 6
    end   = len(calendar) + 1
    instance = 1
  else:
    start = 0
    end   = len(calendar) + 1
    instance = int(cardinal[0])

  counter = 0
  for i in xrange(start, end):
    if weekday == calendar[i]:
      counter = counter + 1

    if counter == cardinal:
      return datetime.date(year, month, i+1)
