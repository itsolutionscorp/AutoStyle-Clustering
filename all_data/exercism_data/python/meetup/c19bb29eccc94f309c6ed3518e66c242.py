__author__ = 'cameron'


from datetime import timedelta
import datetime
import calendar
from dateutil import parser
import itertools


def meetup_day(year, month, day, place):

    thedate = datetime.date(year, month, day=1)
    #print thedate

    alldates = list()

    startday = int(calendar.monthrange(year, month)[0])

    noofdays = int(calendar.monthrange(year, month)[1])

    startdayname = calendar.day_name[startday]

    days = [datetime.date(year, month, i).strftime('%A') for i in range(1, noofdays+1)]
    index = days.index(startdayname)
    alldays = days[index:] + days[:index]

    for i in range(noofdays):
        alldates.append(str(thedate + timedelta(days=i)))

    daypos = [i for i, x in enumerate(alldays) if x == day]



    if place == '1st':
        when = alldates[int(daypos[0])]
        then = datetime.datetime.strptime(when, '%Y-%m-%d').date()
        return then

    if place == '2nd':
        when = alldates[daypos[1]]
        then = datetime.datetime.strptime(when, '%Y-%m-%d').date()
        return then

    if place == '3rd':
        when = alldates[daypos[2]]
        then = datetime.datetime.strptime(when, '%Y-%m-%d').date()
        return then

    if place == 'teenth':
        pass

    if place == '4th':
        when = alldates[daypos[3]]
        then = datetime.datetime.strptime(when, '%Y-%m-%d').date()
        return then

    if place == 'last':
        when = alldates[daypos[4]]
        then = datetime.datetime.strptime(when, '%Y-%m-%d').date()
        return then



meetup_day(2013, 5, 'Tuesday', '1st')
meetup_day(2013, 2, 'Saturday', 'teenth')
# meetup_day(2012, 2, 'Wednesday', '1st')
# meetup_day(2013, 9, 'Thursday', '1st')
