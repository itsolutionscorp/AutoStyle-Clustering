from datetime import date, timedelta

weekdays = { 'monday':    0,
             'tuesday':   1,
             'wednesday': 2,
             'thursday':  3,
             'friday':    4,
             'saturday':  5,
             'sunday':    6 }

def meetup_day(year, month, dayname, nth):
  weekday = weekdays[dayname.lower()]
  nth = nth.lower()
  one_day = timedelta(days=1)

  meetdate = date(year, month, 1)
  matches = 0
  while meetdate.month == month:
    if meetdate.weekday() == weekday:
      matches += 1
      if (nth == '1st' or
          (nth == '2nd' and matches > 1) or
          (nth == '3rd' and matches > 2) or
          (nth == '4th' and matches > 3) or
          (nth == 'last' and ((date(year, month + 1, 1) - one_day) - meetdate).days < 7) or
          (nth == 'teenth' and meetdate.day > 12)):
        return meetdate
    meetdate += one_day

  return None
