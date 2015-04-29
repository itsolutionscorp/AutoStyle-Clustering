import datetime
engs={'1st':0,'2nd':1,'3rd':2,'4th':3,'last':4,'teenth':2}
wdays={'Monday':0,'Tuesday':1,'Wednesday':2,'Thursday':3,'Friday':4,'Saturday':5,'Sunday':6}
def meetup_day(year,month,wday,number):
    weekday_1=datetime.date(year,month,1).weekday()
    if weekday_1<5:
        timedelta=datetime.timedelta(wdays[wday]+7*engs[number]-weekday_1)
    else:
        timedelta=datetime.timedelta(wdays[wday]+7*engs[number]-weekday_1+7)
    return datetime.date(year,month,1)+timedelta
