import calendar

def meetup_day(year_, month_, weekday_, weekNumber_):
    weekNumber = dict(zip('last 1st 2nd 3rd 4th'.split(), range(-1,4)))
    dayOfWeek = dict(zip('monday tuesday wednesday thursday friday saturday sunday'.split(),
                         range(7))) 
    
    listOfDays = [x for x in calendar.Calendar().itermonthdates(year_, month_) 
           if x.month == month_ and x.weekday() == dayOfWeek[weekday_.lower()]
    ]  
    
    if weekNumber_.lower() == 'teenth':
        for x in listOfDays:
            if 13<= x.day <=19:
                return x
            
    return listOfDays[weekNumber[weekNumber_]]
