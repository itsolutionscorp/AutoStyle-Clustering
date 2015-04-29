'''
Created on Sep 24, 2014

@author: Adam Smith
'''

from datetime import date

def meetup_day(year:int, month:int, dow:str, qualifier:str):
    try:
        dow = {"Monday":1, "Tuesday":2, "Wednesday":3, "Thursday":4,
               "Friday":5, "Saturday":6, "Sunday":7}[dow.capitalize()]
    except KeyError:
        raise ValueError("dow must be Monday-Sunday")
    try:
        day_range = {"teenth":range(13,20), "1st":range(1,8),
                     "2nd":range(8,15), "3rd":range(15,22),
                     "4th":range(23,30), "last":range(31,20,-1)}[qualifier.lower()]
    except KeyError:
        raise ValueError("qualifier must be one of 'teenth','1st-4th','last'")
    for day in day_range:
        try:
            _date = date(year, month, day)
        except ValueError:
            continue
        else:
            if _date.isoweekday() == dow:
                return _date
