from datetime import date,timedelta



'''Helper functions
'''

def find_next_weekday(startdate,weekdayname):
    '''
    finds the next occurence of weekdayname given a date, 
    returns the input date if it matches the name
    '''
    testday = startdate
    daynames = ('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday')
    
    for x in range(7):
        if daynames[testday.weekday()] == weekdayname:
            return testday
        testday += timedelta(1)
    
    raise RuntimeError("Couldn't find a " + weekdayname + " within 7 days of: " + startdate.__str__())
    return False  

def find_previous_weekday(startdate,weekdayname):
    '''
    finds the previous occurence of weekdayname given a date, 
    returns the input date if it matches the name
    '''
    testday = startdate
    daynames = ('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday')
    
    for x in range(7):
        if daynames[testday.weekday()] == weekdayname:
            return testday
        testday -= timedelta(1)
    
    raise RuntimeError("Couldn't find a " + weekdayname + " within 7 days of: " + startdate.__str__())
    return False  
    
def last_day_of_month(any_day):
    next_month = any_day.replace(day=28) + timedelta(days=4)  #go into the next month for sure (32)
                                                              # so we can deduct the day of month 
                                                              #to get to the last day of previous month
    return next_month - timedelta(days=next_month.day)

'''
   MAIN function
'''
def meetup_day(year,month,dayname,spec):
    specifiers = {'1st'   : get_first,    # we'll use this as a switch replacement to call the function 
                  '2nd'   : get_second,   # that matches the spec parameter
                  '3rd'   : get_third,
                  '4th'   : get_fourth,
                  'last'  : get_last,
                  'teenth': get_teenth}
    
    if spec not in specifiers:
        raise RuntimeError("invalid specifier: "+ spec + ", should be one of: " + specifiers.keys().__str__())
        return False
    
    return specifiers[spec](year,month,dayname)


def get_first(year,month,dayname):    
    finddate = date(year,month,1)
    return find_next_weekday(finddate, dayname)


def get_second(year,month,dayname):
    finddate = get_first(year,month,dayname) #get the first date the day occurs
    finddate += timedelta(1) # add one day
    return find_next_weekday(finddate, dayname) # find the next after the first


def get_third(year,month,dayname):
    finddate = get_second(year,month,dayname) #get the second date the day occurs
    finddate += timedelta(1) # add one day
    return find_next_weekday(finddate, dayname)


def get_fourth(year,month,dayname):
    finddate = get_third(year,month,dayname) #get the third date the day occurs
    finddate += timedelta(1) # add one day
    return find_next_weekday(finddate, dayname)


def get_last(year,month,dayname):   
    finddate = date(year,month,last_day_of_month(date(year,month,28)).day)
    return find_previous_weekday(finddate, dayname)

    
def get_teenth(year,month,dayname):
    finddate = date(year,month,13)
    return find_next_weekday(finddate, dayname)
    
