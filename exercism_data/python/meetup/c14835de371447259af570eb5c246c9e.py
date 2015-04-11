from datetime import date
def meetup_day(year,month,day_string,description):
    keys=["Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"]
    values=range(7)
    days=dict(zip(keys,values))
    descriptions={'1st':1,'2nd':2,'3rd':3,'4th':4,"last":0}
    if description=="teenth":
        return teenth_day(year,month,days[day_string])
    else:
        return meet_day(year,month,days[day_string],descriptions[description])

def valid_date(year,month,day):
    try:
        return date(year,month,day)
    except ValueError:
        return None


def teenth_day(year,month,day):
    cands=[valid_date(year,month,d) for d in range(1,32)]
    cands=[cand for cand in cands if cand and (13<=cand.day<=19) and cand.weekday()==day]
    return cands[0]

def meet_day(year,month,day,n):
    cands=[valid_date(year,month,d) for d in range(1,32)]
    cands=[cand for cand in cands if cand and cand.weekday()==day]
    return cands[n-1]
