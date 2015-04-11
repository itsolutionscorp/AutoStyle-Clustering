from datetime import date, timedelta

weekdays = { 'monday': 0,
             'tuesday': 1,
             'wednesday': 2,
             'thursday': 3,
             'friday': 4,
             'saturday': 5,
             'sunday': 6 }

nths = { '1st': 0,
         '2nd': 1,
         '3rd': 2,
         '4th': 3,
         'last': -1 }

def meetup_day(year, month, dayname, nth):
  lastday = (date(year, month + 1, 1) - timedelta(days=1)).day
  matchdays = filter(lambda d: date(year, month, d).weekday() == weekdays[dayname.lower()], range(1, lastday + 1))
  if nth.lower() == 'teenth':
    for d in matchdays:
      if date(year, month, d).day > 12:
        return date(year, month, d)
  else:
    return date(year, month, matchdays[ nths[nth.lower()] ])
