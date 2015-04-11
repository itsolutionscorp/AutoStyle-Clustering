from datetime import date
from datetime import timedelta

day_names = ['Monday', 'Tuesday', 'Wednesday', 'Thursday',
  'Friday', 'Saturday', 'Sunday' ]

def meetup_day(year, month, wk_day, descr):
  first_day = date(year, month, 1).weekday()
  day_no = day_names.index(wk_day)
  if ( descr == 'teenth' ):
    return date(year, month, 13 + ( 2+day_no-first_day )%7)
  if ( descr[0].isnumeric() ):
    return date(year, month, 1 + ( day_no-first_day )%7 + 7*( int(descr[0])-1 ))
  last_day = date(year, month+1, 1) + timedelta( days=-1 )
  last_no = last_day.weekday()
  if ( descr == 'last' ):
    return last_day + timedelta(days = -( day_no-last_no )%7)
