from datetime import date,timedelta

def meetup_day(bd_yr,bd_mo,bd_day,iteration):
    #Translate input day name into integer to match date.weekday().
    day_translate = {'Monday':0,'Tuesday':1,
                     'Wednesday':2,'Thursday':3,
                     'Friday':4,'Saturday':5,'Sunday':6}
    
    #Create date object at day 1.
    meetup_date = date(bd_yr,bd_mo,1)

    #Find the first monthly iteration of the specified day.
    while meetup_date.weekday() != day_translate[bd_day]:
        meetup_date += timedelta(days=1)

    #Add days until criterion is met, then return date.
    if iteration == '1st':
        return meetup_date
    elif iteration == '2nd':
        return meetup_date + timedelta(days=7)
    elif iteration == '3rd':
        return meetup_date + timedelta(days=14)
    elif iteration == '4th':
        return meetup_date + timedelta(days=21)
    elif iteration == 'teenth':
        while not meetup_date.day in [13,14,15,16,17,18,19]:
            meetup_date += timedelta(days=7)
        return meetup_date
    elif iteration == 'last':
        while not (meetup_date + timedelta(days=7)).month == (bd_mo + 1):
            meetup_date += timedelta(days=7)
        return meetup_date
    else:
        raise Exception('Enter a correct day iteration.')
