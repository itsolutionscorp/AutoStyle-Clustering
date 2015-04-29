from calendar import monthrange
from datetime import date

def meetup_day(Y,m,dayOfWeek,w):
    lastDayOfMonth = monthrange(Y,m)[1]
    daysInMonth = (date(Y,m,day) for day in range(1, lastDayOfMonth + 1))

    r = [aDate for aDate in daysInMonth if dayName(aDate) == dayOfWeek]

    if w == 'teenth':
        return next(d for d in r if 13 <= d.day <= 19)
    elif w == 'last':
        return r[-1]
    return r[int(w[0]) - 1]

def dayName(aDate):
	return aDate.strftime('%A')
