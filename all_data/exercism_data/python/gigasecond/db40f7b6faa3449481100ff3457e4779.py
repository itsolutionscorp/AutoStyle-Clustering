from datetime import date, timedelta

# Calculate the "giga birthday" given a particular birthdate
# Giga birthday = birthdate + 1 gigasecond (10**9 seconds)
def add_gigasecond(birthdate):

    # Check that we are getting a valid input
    if isinstance(birthdate,date):

        # Return the given date (birthdate) plus 1 gigasecond (10**9)
        return birthdate + timedelta(seconds=10**9)
    else:
        # The input was not valid
        return None
