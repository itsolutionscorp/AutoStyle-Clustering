from datetime import date,timedelta

def meetup_day(year,month,day,iteration):
    
    day_translate = {'Monday':0,'Tuesday':1,
                     'Wednesday':2,'Thursday':3,
                     'Friday':4,'Saturday':5,'Sunday':6}

    meetup_date = date(year,month,1)

    while meetup_date.weekday() != day_translate[day]:
        meetup_date += timedelta(days=1)

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
