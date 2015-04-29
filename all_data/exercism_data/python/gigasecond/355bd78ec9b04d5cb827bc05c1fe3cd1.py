from datetime import date
from datetime import datetime
from datetime import time
from datetime import timedelta

# Calculate the "giga birthday" given a particular birthdate
# Giga birthday = birthdate + 1 gigasecond (10**9 seconds)
def add_gigasecond(birthdate):

    # Check that we are getting a valid input
    if birthdate is not None and isinstance(birthdate,date):

        # Store the value of a gigasecond for later calculations
        gigasecond = 10**9

        # Turn birthdate into a datetime, so that we can easily add 1 gigasecond to it
        birthdate = datetime.combine(birthdate,time())

        # Calculate the "giga birthday" by adding 1 gigasecond to the birthdate
        giga_birthday = birthdate + timedelta(seconds=gigasecond)

        # Convert the datetime object back into a date
        return giga_birthday.date()
    else:
        # The input was not valid
        return None
