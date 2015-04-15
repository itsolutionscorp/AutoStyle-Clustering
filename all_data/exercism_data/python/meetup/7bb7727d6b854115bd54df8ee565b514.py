#meetup.py


import datetime
import calendar

def meetup_day(year,month,*args):
    terms=match_day(args)
    #(0, 'teenth')
    cal=calendar.monthcalendar(year,month)
    daylist= [i[terms[0]] for i in cal]
    for x in daylist:
        if terms[1] in x:
            return datetime.date(year, month, x)
    return mdate

def match_day(terms):
    text = [i for i in terms]
    return find_day_name(text[0]),text[1]

def find_day_name(s):    
    if s == 'Monday':
	return 0
    if s == 'Tuesday':
	return 1

    return 6


    
       # if terms[i]
