from datetime import date,timedelta

def meetup_day(year,month,day,iteration):

    #Translate input day name into integer to match date.weekday().
    day_translate = {'Monday':0,'Tuesday':1,
                     'Wednesday':2,'Thursday':3,
                     'Friday':4,'Saturday':5,'Sunday':6}

    #Create date object at day 1.
    meetup_date = date(year,month,1)

    #Find the first monthly iteration of the specified day.
    while meetup_date.weekday() != day_translate[day]:
        meetup_date += timedelta(days=1)

    #Add days until criterion is met, then return date.
    if iteration[0].isdigit():
        return meetup_date + timedelta(days=(int(iteration[0])-1)*7)
    elif iteration == 'teenth':
        while meetup_date.day < 13:
            meetup_date += timedelta(days=7)
        return meetup_date
    elif iteration == 'last':
        while not (meetup_date + timedelta(days=7)).month == (month + 1):
            meetup_date += timedelta(days=7)
        return meetup_date
    else:
        raise Exception('Enter a correct day iteration.')
