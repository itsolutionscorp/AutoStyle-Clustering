###
###
### Gigasecond exercise for exercism.io
###
###

import datetime

def add_gigasecond(birthday):


    ###Calculate gigaseconds
    secs = 10**9

    ###Break seconds into days
    numDays = secs / 86400

    ###Make a datetime object out of gigasecond in days
    gigAnniversary = datetime.timedelta(days = numDays)

    ###Add it to the birthday we're given
    endDate = birthday + gigAnniversary

    return endDate
