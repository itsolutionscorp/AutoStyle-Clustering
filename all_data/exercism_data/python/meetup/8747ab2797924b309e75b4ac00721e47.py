from datetime import date

def meetup_day(year, month, day, expr):
    mods = {
    'teenth': 0,
    'last' : 5,
    '1st' : 1,
    '2nd' : 2,
    '3rd' : 3,
    '4th' : 4,
    }
    days = {
    'Monday': 0,
    'Tuesday': 1,
    'Wednesday':2,
    'Thursday':3,
    'Friday':4,
    'Saturday':5,
    'Sunday':6,
    }
    #the if statement checks the expression we are looking for
    #then all the values plus a range of days is passed to the help function
    if  mods[expr] == 0:
        return(help(year,month,day,days,13,20))
    elif mods[expr] == 1:
        return(help(year,month,day,days,1,8))
    elif mods[expr] == 2:
        return(help(year,month,day,days,7,15))
    elif mods[expr] == 3:
        return(help(year,month,day,days,14,22))
    elif mods[expr] == 4:
        return(help(year,month,day,days,21,29))
    elif mods[expr] == 5:
        return(help(year,month,day,days,25,32))

    #this functions searches for the day argument in a preset range
    #according to the expr argument
def help(year,month,day,days,start,stop):
    for i in range(start,stop):
            if (date(year,month,i).weekday() == days[day]):
                return date(year,month,i)
    
