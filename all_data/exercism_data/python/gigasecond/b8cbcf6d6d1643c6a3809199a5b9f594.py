#Exercism activity #6
from datetime import date, timedelta

def add_gigasecond(birthday):
    #converting the seconds to days (assuming perfect conversions)
    minutes = 10**9 / 60
    hours = minutes / 60
    days = hours / 24
    
    #Using the timedelta object to "add" days to the timedate object.
    anniversary = birthday + timedelta(days = days)

    return anniversary
