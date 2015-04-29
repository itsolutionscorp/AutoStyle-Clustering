from datetime import datetime, date

def meetup_day(year, month, day, occurence):
  first_of_month = datetime(year, month, 1)
  days_in_month = 31 if month == 12 else (datetime(year, month + 1, 1) - first_of_month).days
  first_day_of_month = first_of_month.weekday()
  weekday_map = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}

  first_date = 1 + (weekday_map[day] - first_day_of_month) % 7
  occurences = (days_in_month - first_date) // 7

  if occurence == 'teenth':
    k = 1 if first_date >= 6 else 2
  elif occurence == 'last':
    k = occurences
  elif occurence == 'first':
    k = 0
  else:
    k = int(occurence[0]) - 1

  if k  > occurences + 1:
    raise(Exception('No ' + occurence + ' ' + day + ' in given date.'))

  return date(year, month, first_date + 7*k)
