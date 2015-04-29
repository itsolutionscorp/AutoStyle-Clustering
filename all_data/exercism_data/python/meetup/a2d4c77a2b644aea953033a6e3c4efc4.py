'''
Created on Oct 20, 2014

@author: jbarni00
'''
#meetup_day(2013, 5, 'Monday', 'teenth')
import datetime
import calendar

def meetup_day(year, month, dayofweek, increment):
    # Get day of week of first of month


    week   = {'Sunday': 0, 
              'Monday' :1, 
              'Tuesday': 2, 
              'Wednesday': 3, 
              'Thursday' : 4,  
              'Friday': 5, 
              'Saturday': 6 }
    

    dayone, numberofdays = calendar.monthrange(year, month)
    # Sunday is a special case, normaize to 0-7

    if dayone == 6:
        dayone = 0
    else:
        dayone = dayone+1

    if dayone <= week[dayofweek]:
        firstdate = 1 + ( week[dayofweek] - dayone)
    else:
        firstdate = 1 + 7 - (  dayone - week[dayofweek])

    if increment == 'teenth':
        potentialday = firstdate
        while potentialday < 13:
            potentialday += 7
        return datetime.date(year, month, potentialday)

    if increment == 'first' or increment == '1st':
        return datetime.date(year, month, firstdate)
    
    if increment == '2nd':
        return datetime.date(year, month, firstdate +7 )
    
    if increment == '3rd':
        return datetime.date(year, month, firstdate + 14)
    
    if increment == '4th':
        return datetime.date(year, month, firstdate + 21 )
    
    if increment == 'last':
        potentialday = firstdate + 35
        while potentialday > numberofdays:
            potentialday -=7
        return datetime.date(year, month, potentialday)
        
            
