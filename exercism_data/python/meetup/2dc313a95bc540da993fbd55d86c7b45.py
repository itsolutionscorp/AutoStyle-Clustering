from datetime import date
from datetime import timedelta



def meetup_day(year,month,targetday,targetweek):
    daymap = {'Monday':     0,
              'Tuesday':    1,
              'Wednesday':  2,
              'Thursday':   3,
              'Friday':     4,
              'Saturday':   5,
              'Sunday':     6}

    weekmap = {'1st':     1,
               '2nd':    2,
               '3rd':     3,
               '4th':    4}
    
    targetday = daymap[targetday]

    if targetweek == 'teenth':

        d = date(year,month,13)

        while d.weekday() != targetday:
            d += timedelta(1)

        return d

    elif targetweek == 'last':
        d = date(year,month+1,01) + timedelta(-1)

        while d.weekday() != targetday:
            d += timedelta(-1)

        return d

    else:
        d = date(year,month,01)
        while d.weekday() != targetday:
            d += timedelta(1)

        week = 1
        targetweek = weekmap[targetweek]

        while week < targetweek:
            d += timedelta(7)
            week +=1
        return d
