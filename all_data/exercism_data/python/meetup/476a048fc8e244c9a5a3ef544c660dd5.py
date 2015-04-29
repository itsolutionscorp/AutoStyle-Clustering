# Python Exercism #7 Meetup

import datetime
    
# converting to datetime .weekday() 
days = {'Monday':0,
        'Tuesday':1,
        'Wednesday':2,
        'Thursday':3,
        'Friday':4,
        'Saturday':5,
        'Sunday':6 }

# returns number of days in a month
def days_in_month(year,month):
    d = datetime.date(year,(month+1)%12,1)
    delta = datetime.timedelta(days=-1)
    return (d+delta).day

# returns array of possible dates
def ndays_in_month(year,month,nday):
    ndays_in_month = [] 
    d = datetime.date(year,month,1)
    for date in range(0,days_in_month(year,month)):
        if nday == d.weekday():
            ndays_in_month.append(d)
        else: 
            pass
        delta = datetime.timedelta(days=1)
        d += delta
    return ndays_in_month
        

def meetup_day(year,month,dow,st):
    options = ndays_in_month(year,month,days[dow])
    if st == '1st':
        return options[0]
    elif st == '2nd': 
        return options[1]
    elif st == '3rd':
        return options[2]
    elif st == '4th':
        return options[3]
    elif st == 'last':
        return options[-1]
    elif st == 'teenth':
        for option in options:
            if option.day in range(13,20):
                return option
    else:
        return "date not found"


#mini testing
if __name__ == '__main__':
    print meetup_day(2013,2,'Monday','2nd')
