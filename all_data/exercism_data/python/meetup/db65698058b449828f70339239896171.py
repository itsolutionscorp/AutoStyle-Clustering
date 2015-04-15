import datetime


def meetup_day(year, month, weekday, rank):
    x = datetime.date(year, month, 1)
    start = x
    if ranks.index(rank) == 6:
        x = calcDay(x, ranks[1], weekday)
        if x.day / 10 == 0:
            x = calcDay(x, ranks[1], weekday)
    if ranks.index(rank) == 5:
        rank = ranks[4]
    if ranks.index(rank) < 5:
        x = calcDay(x, rank, weekday)
    if start.month < x.month:        
        raise Exception("Month too short!")  
    return x        

def calcDay(xDate, xRank, xWeekday):
    result = (xDate +
        datetime.timedelta(days = first(xDate.weekday(),
                week.index(xWeekday)) + 7 * ranks.index(xRank)))
    return result        

def first(d1, d2):
    x = 0
    if d1 > d2:
        x = 7
    return x + d2 - d1
   
week = ['Monday', 'Tuesday', 'Wednesday', 'Thursday',
        'Friday', 'Saturday', 'Sunday']

ranks = ['1st', '2nd', '3rd', '4th', '5th', 'last', 'teenth']
