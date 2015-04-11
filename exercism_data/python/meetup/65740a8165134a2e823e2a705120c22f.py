#William Morris
#exercism.io
#meetup.py

from datetime import date , timedelta

def meetup_day(year, month, weekday, which_weekday):
    weekday_list = ['Monday','Tuesday','Wednesday','Thursday','Friday',
                    'Saturday','Sunday']
    which_weekday_list = ['1st','2nd','3rd','4th','5th','teenth','last']
    weekly_increment = timedelta(days=7)
    #find weekday for first day of month Monday == 0 ... Sunday == 6
    #start with first day of month
    weekday_date = date(year, month, 1)
    #find the days till the first weekday we are looking for as timedelta
    days_till_weekday = timedelta(days=(weekday_list.index(weekday)
                     -weekday_date.weekday()
                     +7)%7)
    #add to start date to find first weekday
    weekday_date += days_till_weekday
    iteration = 0
    weekday_dict = {}
    
    while weekday_date.month == month:
        #gather the dates in a dictionary
        weekday_dict[which_weekday_list[iteration]]= weekday_date
        #check for 'teenth' case
        if 13<=weekday_date.day <=19: weekday_dict['teenth']=weekday_date
        #make current date 'last' date
        weekday_dict['last']=weekday_date
        #increment to next weekday and loop
        weekday_date += weekly_increment
        iteration +=1
    return weekday_dict[which_weekday]
    
        
    
    
    
    
    
    


#test 2013, 5, 'Monday', 'teenth'
