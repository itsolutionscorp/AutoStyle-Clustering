from datetime import date

days = ['Sun', 'Mon', 'Tues', 'Wednes', 'Thurs', 'Fri', 'Satur']

def meetup_day(y, m, dayname, ext):
    teenth = range(13,20)

    #weekday reference where Sunday is 0, Monday is 1 etc.
    d = days.index(dayname[:-3])

    #adjust based on what day the month began on
    offset = date(y, m, 1).weekday()
    
    #d-offset %7 or 7 gets the day that the named day first occurred on in the
    #given month, using the first day to adjust, then makes a list with that
    #numerical day and the same day in every following week
    #NB: doesn't take into account months ending before the 31st
    th_day = range((d-offset)%7 or 7, 32, 7)

    if ext[0] in map(str, range(1,5)):
        #return the Xth day
        return date(y, m, th_day[int(ext[0])-1])
    elif ext == "teenth":
        #adjusts, then returns the "teen" number that corresponds
        return date(y, m, teenth[(d-offset+1)%7])
    elif ext == "last":
        try:
            #the last day of Xday in a 31-day month
            #date() throws a Value error if you give it an out-of-range day
            return date(y, m, th_day[-1])
        except ValueError:
            #so catch it, and return the one before it
            return date(y, m, th_day[-2])
