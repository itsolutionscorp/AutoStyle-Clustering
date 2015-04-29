import datetime
GIGASECOND = 10**9

def add_gigasecond(birthdate):
    return birthdate + datetime.timedelta(seconds=GIGASECOND)    
