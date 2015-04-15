from datetime import date, timedelta

def meetup_day(year, month, weekday, option):

    weekdays = {'Monday' : 0, 'Tuesday' : 1, 'Wednesday' : 2, 'Thursday' : 3, 'Friday' : 4, 'Saturday' : 5, 'Sunday' : 6}
    daynum = weekdays[weekday]

    sign = 1  # default to counting forward from reference day
    if option == '1st':
        refday = date(year, month, 1)
    elif option == '2nd':
        refday = date(year, month, 8)
    elif option == '3rd':
        refday = date(year, month, 15)
    elif option == '4th':
        refday = date(year, month, 22)
    elif option == 'last':
        refday = date(year + month/12, (month+1) % 12, 1) - timedelta(1)  # last day of the month
        sign = -1  # count backwards from last of month
    elif option == 'teenth':
        refday = date(year, month, 13)
    else:
        raise Exception('Invalid option')
        
    dayoffset = daynum - refday.weekday()
    if sign*dayoffset < 0:  # would we be moving in the wrong direction?
        offset = timedelta(dayoffset + 7*sign)  # correct by shifting to the next week
    else:
        offset = timedelta(dayoffset)

    return refday + offset
