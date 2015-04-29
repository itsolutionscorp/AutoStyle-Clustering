import datetime

def add_gigasecond(birth):
    gigasecond = datetime.timedelta(seconds = 10**9) # Define a time delta a gigasecond long
    giga_anniversary = birth + gigasecond # Add gigsecond to the birth date
    return giga_anniversary
