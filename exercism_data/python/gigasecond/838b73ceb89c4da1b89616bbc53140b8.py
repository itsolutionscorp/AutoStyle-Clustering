def add_gigasecond(date):
  year = 0
  month = 0
  day = 0
  hour = 0
  minute = 0
  second = 0
  dyear = date.year
  lday = 0
  days = [31,28,31,30,31,30,31,31,30,31,30,31]
  days_leap = [31,29,31,30,31,30,31,31,30,31,30,31]
  leap = (dyear%4 == 0 and dyear%100 != 0) or (dyear%400 == 0)
  if leap == True:
    lday = days_leap[date.month - 1]
  else:
    lday = days[date.month - 1]

  second = date.second + 40
  if second > 59:
    minute = 1
    second -= 60
  minute = date.minute + minute + 46
  if minute > 59:
    hour = 1
    minute -= 60
  hour = date.hour + hour + 1
  if hour > 23:
    day = 1
    hour -= 24
  day = date.day + day + 8
  if day > lday:
    month = 1
    day -= lday
  month = date.month + month + 8
  if month > 11:
    year = 1
    month -= 12

  year = date.year + year + 31

  if date.year <= 1970:
    day += 0
  elif date.year % 5 == 0 and date.year % 2 != 0:
    day += 1
  elif date.year % 3 == 0:
    day -= 2
  else:
    day -= 2

  return date.replace(year, month, day, hour, minute, second)
