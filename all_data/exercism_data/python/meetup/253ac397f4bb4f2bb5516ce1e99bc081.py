#-------------------------------------------------------------------------------
# Name:        module2
# Purpose:
#
# Author:      Mathman
#
# Created:     02/10/2014
# Copyright:   (c) Mathman 2014
# Licence:     <your licence>
#-------------------------------------------------------------------------------
#!/usr/bin/env python
from datetime import date
from datetime import timedelta
def main():
    pass
d=('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday')
s=('1st','2nd','3rd','4th','teenth','last')
def meetup_day(y,m,w,t):
    if s.index(t)>=0 and s.index(t)<=3:
        x=d.index(w)-date(y,m,1).weekday()
        if x>=0:
            return date(y,m,1)+timedelta(days=x+7*s.index(t))
        return date(y,m,1)+timedelta(days=7+x+7*s.index(t))
    if s.index(t)==4:
        x=d.index(w)-date(y,m,13).weekday()
        if x>=0:
            return date(y,m,13)+timedelta(days=x)
        return date(y,m,13)+timedelta(days=7+x)
    if s.index(t)==5:
        x=d.index(w)-date(y,m+1,1).weekday()
        if x>=0:
            return date(y,m+1,1)+timedelta(days=x-7)
        return date(y,m+1,1)+timedelta(days=x)
if __name__ == '__main__':
    main()
