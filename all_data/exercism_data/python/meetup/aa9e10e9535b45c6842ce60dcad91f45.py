'''
Created on Oct 15, 2014

@author: dhawkins
'''
from datetime import date
from datetime import timedelta

DAY_DICT = {'Sunday' : 6,
            'Monday' : 0,
            'Tuesday' : 1,
            'Wednesday' : 2,
            'Thursday' : 3,
            'Friday' : 4,
            'Saturday' : 5}

ORDER_DICT = {'1st' : 0,
              '2nd' : 1,
              '3rd' : 2,
              '4th' : 3,
              '5th' : 4,
              'last' : -1}

TEENS = (13,14,15,16,17,18,19)

def meetup_day(y, m, d, rank):

    first_day = date(y, m, 1)
    
    while first_day.weekday() != DAY_DICT[d]:
        first_day = first_day + timedelta(days=1)
        
    possible_days = [first_day]
    
    # populate a list with all possible days
    next = possible_days[-1] + timedelta(days=7)
    while next.month == m:
        possible_days.append(next)
        next = possible_days[-1] + timedelta(days=7)
    
    # Find the day that is the teenth or just return the rank
    if rank == 'teenth':
        for one_day in possible_days:
            if one_day.day in TEENS:
                return one_day
    else:
        return possible_days[ORDER_DICT[rank]]
    
