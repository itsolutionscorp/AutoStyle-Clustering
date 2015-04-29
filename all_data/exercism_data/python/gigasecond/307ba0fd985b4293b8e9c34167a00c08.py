from datetime import timedelta

def add_gigasecond(birthdate):
    ''' adds 1 billion seconds to a date, expects datetime as input and returns datetime'''
    gigasecond_days = timedelta(0,10**9)
    return birthdate + gigasecond_days
    
