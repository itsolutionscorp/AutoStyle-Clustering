from calendar import Calendar, day_name
cal_inst = Calendar()
from datetime import date

def meetup_day(year, month, name_of_day, day_of_month):
    
    # take first day of month and add appropriate time according to arguments
    # adding arguments: name_of_day(M, Tu, W, Th, Fr, Sa, Su) & day_of_month(1st, 2nd, 3rd, 4th, teenth, last) 
    
    # convert the names of days into a cardinal list --> [0-6]
    name_of_day_num_list = list(enumerate(day_name))
    for k, v in name_of_day_num_list:
        while name_of_day == v:
            name_of_day_num = k
            break
        else:
            pass
            
    #returns calendar iterator(tuple) for specified year, month (date.day#, wkday#)
    cal = list(cal_inst.itermonthdays2(year, month))
    
    #append a new list with all of that weekdays values (i.e. all Mondays, Tuesdays, etc.)
    new_list = [k for k, v in cal if v == name_of_day_num and k != 0]

    #enumerate new_list
    new_list = list(enumerate(new_list))
    
    #in this new_list, go through and select day that matched ordinal selections
        #if day_of_month == ordinal number
            #choose that number in list
        #if day_of_month == *'teenth':
            #choose number in list that == 13-19
        #if day_of_month == 'last':
            #choose last number in list
                   
    if day_of_month == '1st' or '2nd' or '3rd' or '4th':
        day_of_month_num = ord_to_card(day_of_month)
    else:
        pass
    
    for k, v in new_list:
        if k == ord_to_card(day_of_month):
            day = v
        else:
            if day_of_month == 'teenth':
                for i in xrange(13, 20):
                    if v == i:
                        day = v
                else:
                    pass
            elif day_of_month == 'last':
                for k, v in new_list[-1:]:
                    day = v
            else:
                pass
                   
    return date(year, month, day)
    
################################################
def ord_to_card(value):

    if value == '1st':
        return 0
    elif value == '2nd':
        return 1
    elif value == '3rd':
        return 2
    elif value == '4th':
        return 3
    else:
        return False
