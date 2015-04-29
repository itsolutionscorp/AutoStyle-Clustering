# -*- coding: utf-8 -*-

from datetime import date
import calendar

def meetup_day(year, month, wd, order):
    dict = {'Monday':0,'Tuesday':1,'Wednesday':2,'Thursday':3,'Friday':4, 'Saturday':5,  'Sunday':6}
    lst = calendar.monthcalendar(year, month)
    ret = date(1,1,1)
    matchcnt = 0
    bkflg = False
    for i in lst:
        for y in i:
            if y == 0 :
                continue
            else: 
                weekday = calendar.weekday(year, month, y)
                if weekday == dict[wd]:
                    if order == 'teenth' and y >= 10 and y < 20:
                        ret = date(year, month, y)
                        bkflg = True
                        return ret
                    elif order == '1st' and matchcnt == 0:
                        ret = date(year, month, y)
                        return ret
                    elif order == '2nd' and matchcnt == 1:
                        ret = date(year, month, y)
                        return ret
                    elif order == '3rd' and matchcnt == 2:
                        ret = date(year, month, y)
                        return ret
                    elif order == '4th' and matchcnt == 3:
                        ret = date(year, month, y)
                        return ret
                    elif order == 'last':
                        ret = date(year, month, y)
                    matchcnt += 1
        if bkflg:
            break

    return ret
