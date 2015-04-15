from datetime import date

def is_leap(year):

    if year % 4 == 0:
        if year % 400 == 0:
            return True
        elif year % 100 == 0:
            return False
        else:
            return True
    else:
        return False

def meetup_day(year, month, daystr, nth):

    dtdict = {'Monday' : 0,
              'Tuesday' : 1,
              'Wednesday' : 2,
              'Thursday' : 3,
              'Friday' : 4,
              'Saturday' : 5,
              'Sunday' : 6}

    rangedict = {'teenth' : range(13,20),
                 '1st' : range(1,8),
                 '2nd' : range(8,15),
                 '3rd' : range(15,22),
                 '4th' : range(22,29),
                 'last' : range(25,32)
                }
    
    if month == 2 and nth == 'last' and is_leap(year) == False:
        for i in range(22,29):
            if dtdict[daystr] == date(year, month, i).weekday():
                day = i
    elif is_leap(year) == True:
        for i in range(22,30):
            if dtdict[daystr] == date(year, month, i).weekday():
                day = i
    else:
        for i in rangedict[nth]:
            if dtdict[daystr] == date(year, month, i).weekday():
                day = i
    dt = date(year,month,day)
    return dt
