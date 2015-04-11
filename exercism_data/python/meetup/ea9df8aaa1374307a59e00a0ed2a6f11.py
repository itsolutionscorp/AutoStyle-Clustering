from datetime import date
from datetime import timedelta
def meetup_day(yr,mn,wkdy,ocur):
    wkdy_num = {"Monday":0,"Tuesday":1,"Wednesday":2,"Thursday":3,"Friday":4,"Saturday":5,"Sunday":6}
    teeth = list(range(13,20))
    stdt = date(yr,mn,1)
    daystofirst=0
    if stdt.weekday() > wkdy_num[wkdy]:
        daystofirst = 7 + (wkdy_num[wkdy] - stdt.weekday())
    else:
        daystofirst = wkdy_num[wkdy] - stdt.weekday()
    ocur = ocur[:1]
    if ocur == "l":
        ocur = 5
    elif ocur == "t":
        ocur = 6
    else:
        ocur = int(ocur)
    
    for x in range(ocur):
        ndt = stdt + timedelta(days=daystofirst + 7*x)
        if x == ocur-1 and int(ndt.month) == mn:
            return ndt
        elif ocur == 6:
            if ndt.day in teeth:
                return ndt
        elif int(ndt.month) != mn:
            return ndt + timedelta(days=-7)
