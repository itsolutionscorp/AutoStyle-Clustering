from datetime import date,timedelta

def getFirst(year,month,dow,other):
    iterDate = date(year,month,1)
    while dow.lower() not in (iterDate.strftime('%a').lower(),
                            iterDate.strftime('%A').lower()):
        iterDate += timedelta(days=1)
    return iterDate

def getLast(year,month,dow,other):
    iterDate = getFirst(year,month,dow,other)
    while iterDate.month == month:
        iterDate += timedelta(days=7)
    return iterDate - timedelta(days=7)

def getTeenth(year,month,dow,other):
    iterDate = getFirst(year,month,dow,other)
    while iterDate.day < 13:
        iterDate += timedelta(days=7)
    return iterDate

def meetup_day(year,month,dow,other):
    params = { 'year' : year, 'month' : month, 'dow' : dow, 'other' : other }
    if other in ['first', '1st']:
        return getFirst(**params)
    elif other == 'last':
        return getLast(**params)
    elif other == 'teenth':
        return getTeenth(**params)
    if other not in ['2nd','3rd','4th','5th']:
        raise Exception('Invalid day input - not first, last, teenth or (valid) Xth')    
    xth = other[:-2]
    iterDate = getFirst(**params)
    numDays = (int(xth) - 1) * 7
    iterDate += timedelta(days=numDays)
    if iterDate.month != month:
        raise Exception('Invalid day input - not first, last, teenth or (valid) Xth')    
    return iterDate
