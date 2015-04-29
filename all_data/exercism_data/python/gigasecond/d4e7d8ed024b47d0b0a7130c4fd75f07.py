import datetime

def add_gigasecond(date):

    # Define a timespan of 1,000,000,000 seconds
    gigasecond = datetime.timedelta(seconds=(10**9))
 
    # Return the sum of "date" and "gigasecond."
    return date + gigasecond
