from datetime import date, timedelta

def add_gigasecond(input_date):
    gigasecond = timedelta(seconds = 10**9)
    return(input_date + gigasecond)
