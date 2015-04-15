from datetime import timedelta


def add_gigasecond(bday):
    ''' Adds a gigasecond to the passed date '''

    return bday + timedelta(seconds=10**9)
