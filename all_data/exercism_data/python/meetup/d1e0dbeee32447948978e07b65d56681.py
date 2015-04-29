import datetime
from calendar import monthrange

WEEKDAY_NAMES = {'Monday' : 0, 'Tuesday' : 1, 'Wednesday' : 2, 'Thursday' : 3, 'Friday' : 4, 'Saturday' : 5, 'Sunday' : 6}

def meetup_day(year, month, weekday, designation):
  initDay = 1
  if designation == 'teenth':
  	initDay = 10
  	designation = '1st'
  meetupDate = datetime.date(year, month, initDay)
  delta = WEEKDAY_NAMES[weekday]-meetupDate.weekday()
  if delta < 0:
    delta = 7 + delta
  meetupDate = meetupDate + datetime.timedelta(days=delta)
  numberOfTimes = 4
  if designation != 'last':
    numberOfTimes = int(designation[0])
  for i in range(0, numberOfTimes - 1):
    meetupDate = meetupDate + datetime.timedelta(days=7)
  if designation == 'last':
  	if (monthrange(year, month)[1] - meetupDate.day) >= 7:
  		meetupDate = meetupDate + datetime.timedelta(days=7)
  return meetupDate
