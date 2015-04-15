from datetime import date

def add_gigasecond(query_date):
    
    # Work out how many days are in a gigasecond
    secs = 10**9
    mins = secs/60
    hrs = mins/60
    days = hrs/24

    # Get the ordinal date
    query_date = query_date.toordinal()

    # Add the number of days in a gigasecond to the ordinal date
    return_date = query_date + days

    # Convert the new ordinal to regular date object and return it
    return date.fromordinal(return_date)
