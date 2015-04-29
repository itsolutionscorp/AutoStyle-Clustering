from datetime import date

def meetup_day(year, month, dayName, kind):
    if kind == '1st':
        return _get_meetup_day(0, 8, year, month, dayName)
    elif kind == '2nd':
        return _get_meetup_day(6, 15, year, month, dayName)
    elif kind == 'teenth':
        return _get_meetup_day(12, 20, year, month, dayName)
    elif kind == '3rd':
        return _get_meetup_day(14, 21, year, month, dayName)
    elif kind == '4th' or kind == 'last':
        return _get_meetup_day(21, 32, year, month, dayName)



def _get_meetup_day(startDate, endDate, year, month, dayName):
    d = startDate
    while(d < endDate):
        d += 1
        thisDayName = date(year, month, d).strftime('%A')
        if thisDayName == dayName:
            return date(year, month, d)
