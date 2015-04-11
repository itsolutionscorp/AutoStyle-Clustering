import datetime

def add_gigasecond(birthdate):

    GIGASECOND = 1000000000

    SECONDS_PER_DAY = 86400

    diff = datetime.timedelta(days=(GIGASECOND/SECONDS_PER_DAY))

    futureDate = birthdate + diff

    return futureDate
