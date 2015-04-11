'''
Created on Oct 20, 2014

@author: jbarni00
'''
#meetup_day(2013, 5, 'Monday', 'teenth')
import datetime
import calendar

def meetup_day(year, month, dayofweek, increment):
    # Get day of week of first of month


    week   = {'Sunday': 6, 
              'Monday' :0, 
              'Tuesday': 1, 
              'Wednesday': 2, 
              'Thursday' : 3,  
              'Friday': 4, 
              'Saturday': 5 }
    

    dayone, numberofdays = calendar.monthrange(year, month)

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
        
            
