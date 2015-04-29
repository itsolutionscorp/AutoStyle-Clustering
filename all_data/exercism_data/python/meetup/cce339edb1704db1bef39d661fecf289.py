def meetup_day(year, month, day, description):

    from datetime import date
    
    # find the day of the first of the month: 0=Mon ... 6=Sun
    firstdt = date(year, month, 1)
    day1 = firstdt.weekday()
    
    # Convert the day argument into a 0-6 code as above
    daydict = dict(zip(['Monday','Tuesday','Wednesday','Thursday',
                        'Friday','Saturday','Sunday'],
                       range(7)))
    dayind = daydict[day]
    
    # date of the earliest occurence of that day for that month
    earliestdate = 1 + (dayind - day1) % 7 
    
    # now check over the possible descriptions and add the approp
    # number of multiple of seven days
    if description == '1st':
        daynum = earliestdate
    elif description == '2nd':
        daynum = earliestdate + 7
    elif description == '3rd':
        daynum = earliestdate + 14
    elif description == '4th':
        daynum = earliestdate + 21

    elif description == '5th':
        # include a sanity check by adding 28 days to earliest date
        # and seeing if it's still the same month
        earliestdateobj = date(year, month, earliestdate)
        twentyeight_days_later = \
            date.fromordinal(earliestdateobj.toordinal() + 28 )
        if twentyeight_days_later.month == month:
            daynum = earliestdate + 28
        else:
            raise Exception('Invalid entry.')

    elif description == 'teenth':
        # find the first multiple of seven past the 13th
        daynum = earliestdate
        while daynum < 13:
            daynum = daynum + 7        

    elif description == 'last':
        #find the last date of the month
        if month == 12:
            lastdaynum = 31
        else:
            nextmonthday1 = date(year,month+1,1)
            lastdate = date.fromordinal(nextmonthday1.toordinal()-1)
            lastdaynum = lastdate.day
        # use the fourth or fifth week, using lastdaynum as a limit
        daynum = earliestdate + 21
        if daynum + 7 <= lastdaynum:
            daynum = daynum + 7
            
    return date(year, month, daynum)
