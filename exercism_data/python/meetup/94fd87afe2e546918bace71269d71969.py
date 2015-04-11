import datetime

def meetup_day(myear, mmonth, mday, mday2):
    mdayint = 0
    mday2int = 0
        
    mday_dict = {'Monday':0,'Tuesday':1,'Wednesday':2,'Thursday':3,'Friday':4,'Saturday':5,'Sunday':6}
    mdayint = mday_dict.get(mday)
    
    mday2_dict = {'1st':0,'2nd':1,'3rd':2,'4th':3,'last':-1,'teenth':5}
    mday2int = mday2_dict.get(mday2)
    
    dt = datetime.date(myear,mmonth,1)
    dow_lst = []
    while dt.weekday() != mdayint:
        dt = dt + datetime.timedelta(days=1)
    while dt.month == mmonth:
        dow_lst.append(dt)
        dt = dt + datetime.timedelta(days=7)
    if mday2int < 4:
        return dow_lst[mday2int]  # may raise an exception if slicing is wrong
    elif mday2int > 4:
        for choice in dow_lst:
            if (choice.day >= 13 and choice.day <= 19):
                return choice
