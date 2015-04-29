from datetime import date,timedelta

def add_gigasecond(birthdate):
    gigasec = timedelta( days = 11574 , hours = 1 , minutes = 46 , seconds = 40 )
    gsdate = birthdate + gigasec
    return gsdate
