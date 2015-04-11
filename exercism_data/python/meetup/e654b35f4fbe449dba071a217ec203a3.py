import calendar

def meetup_day(year_, month_, weekday_, weekNumber_):
    weekNumber = dict(zip('last 1st 2nd 3rd 4th'.split(), range(-1,4)))
    dayOfWeek = dict(zip('monday tuesday wednesday thursday friday saturday sunday'.split(),
                         range(7))) 
    listOfDays = []
    
    cal = calendar.Calendar().monthdatescalendar(year_, month_) 
    
    for x in range(len(cal)):
        for y in range(len(cal[x])):
            if cal[x][y].weekday() == dayOfWeek[weekday_.lower()] and cal[x][y].month == month_:
                listOfDays.append(cal[x][y])
    
    if weekNumber_.lower() == 'teenth':
        for x in listOfDays:
            if x.day >=13 and x.day <=19:
                return x
    
    return listOfDays[weekNumber[weekNumber_]]
    
meetup_day(2012, 6, 'Monday', '1st')
