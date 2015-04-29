'''
Created on Oct 2, 2014

@author: dulshani

RESOURCE SITE
http://pymotw.com/2/calendar/

NOTE: pprint a nested list much readabke!
'''
from datetime import date
import calendar

def teenth(cal, week_day):
    for week in range(1,4):         #teenths would always fall in 2nd-4th week of the month
        if cal[week][week_day] in range(13,20):
            return cal[week][week_day]   
    
    
def not_teenth(cal, week_day, occ):
    weeks = len(cal)-1                  #number of weeks in month
    mapocc = {"1st":0, "2nd":1, "3rd":2, "4th":3, "last":weeks}
    if cal[0][week_day] != 0:           #day occurs in first week
        return cal[mapocc[occ]][week_day] 
    else:
        return cal[mapocc[occ]+1][week_day]
    

def meetup_day(year, month, day, occurence):
    cal = calendar.monthcalendar(year,month)
    days_of_week = {"Monday":0, "Tuesday":1, "Wednesday":2, "Thursday":3, "Friday":4, "Saturday":5, "Sunday":6}
    week_day = days_of_week[day]
    if occurence == "teenth":
        meetup_date = date(year, month, teenth(cal, week_day))
    else:
        meetup_date = date(year, month, not_teenth(cal, week_day, occurence))
    return meetup_date
    
