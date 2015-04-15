from datetime import date
from calendar import monthrange

#List of the days of the week
DAYS = ['monday',
        'tuesday',
        'wednesday',
        'thursday',
        'friday',
        'saturday',
        'sunday']

#Dictionary for which ordinals correspond with which numbers
NUMS = {'1st':1,
        'first':1,
        '2nd':2,
        'second':2,
        '3rd':3,
        'third':3,
        '4th':4,
        'fourth':4,
        '5th':5,
        'fifth':5}
        

def teenth(year, month, day):
    '''Calculate the teenth for a given year, month and day

       for example the Fridayteenth of March 1989 is
       teenth(1989, 3, 'friday)
    '''

    #what number day the 13th of the month is
    thirteen = date(year,month,13).weekday()

    #how many days after the 13th is the day the user wants
    offset = (day - thirteen) % 7

    #the date the user wants
    the_date = 13 + offset
    
    return date(year, month, the_date)

def nth(year, month, day, ordinal):
    '''Calculate the of given day for a year and month

       for example the 3rd Friday of
       June 1989 = nth(1989, 6, 'friday', '3rd')

    '''
    #what number day the first day of the month is
    first = date(year, month, 1).weekday()

    #how many days after the first is the day of the week the user wants
    offset = (day - first) % 7

    #first of this type of day in the month
    first_date = 1 + offset

    #the date the user is looking for
    the_date = first_date + (ordinal-1)*7
    return date(year, month, the_date)

def last(year, month, day):
    '''Calculate last day for a year and month

       for example the last Friday of
       June 1989 = last(1989, 6, 'friday')

    '''

    #how many days in this month
    days_in_month =  monthrange(year, month)[1]

    #day of week the last day of this month falls on
    last = date(year, month, days_in_month).weekday()

    #how many days before the last date is the day the
    #user wants
    offset = (last - day) % 7

    #date the user is looking for
    the_date = days_in_month - offset
    return date(year, month, the_date)
    
def meetup_day(year, month, day, descriptor):
    '''Calcaulte the date of a meetup day

       Give the Year month and day and a descriptor
       which is either '1st', 'first', '2nd', etc...
       'teenth', or 'last'

       For example meetup_day(1989, 5, 'tuesday', 'teenth')
       will give the tuesdayteenth for May 1989
    '''
    
    descriptor = descriptor.lower()

    #what number day the day of the week the user wants is
    day_number = DAYS.index(day.lower())
    
    if descriptor == 'teenth':
        return teenth(year, month, day_number)
        
    elif descriptor in NUMS:
        #what number the provided ordinal corresponds with
        ordinal = NUMS[descriptor]
        return nth(year, month, day_number, ordinal)
        
    elif descriptor == 'last':
        return last(year, month, day_number)
        
    else:
        return "invalid request"
