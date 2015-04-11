import datetime
import calendar

def meetup_day(year, month, dow, which):
    #13 14 15 16 17 18 19
    firstOfMonth = datetime.datetime(year, month, 1)
    (first_weekday, daysInMonth) = calendar.monthrange(year, month)
    daysOfWeek = []
    
    for offset in range(0,7):
        myDate = firstOfMonth - datetime.timedelta(days=first_weekday-offset)
        daysOfWeek.append(myDate.strftime('%A'))

    myDow = daysOfWeek.index(dow)
    print myDow
    print first_weekday
    firstMyDay = myDow - first_weekday
    if firstMyDay < 1:
        firstMyDay += 7
    print firstMyDay

    try:
        intwhich = int(which[0])
    except ValueError:
        intwhich = None

    if intwhich:
        print intwhich
    elif which.lower() == 'first':
        intwhich = 1
    elif which.lower() == 'last':
        print first_weekday
        #start with first_weekday, add 7*4, check if > daysInMonth
        lastDaysOfWeek = {}
        for index, d in enumerate(daysOfWeek):
            myDay = 
            lastDaysOfWeek[d] = 
    else:   # which should be teenth
        a = 0
        #intwhich = ??

if __name__ == "__main__":

    meetup_day(2014, 11, 'Monday', 'last')
