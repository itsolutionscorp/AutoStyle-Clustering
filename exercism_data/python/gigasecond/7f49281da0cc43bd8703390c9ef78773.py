from datetime import date, timedelta


def add_gigasecond(birthdate):
    '''Return 10**9 seconds after birthdate'''

    return (birthdate + timedelta(seconds=10**9)) 
