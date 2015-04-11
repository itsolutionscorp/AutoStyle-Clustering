from datetime import date, timedelta


def add_gigasecond(bday) -> date:
    ''' Adds a gigasecond to the passed date '''

    return bday + timedelta(seconds=10**9)
