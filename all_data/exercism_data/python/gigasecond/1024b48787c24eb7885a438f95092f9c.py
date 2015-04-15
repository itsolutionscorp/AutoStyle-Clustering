#
# gigasecond functions
#

import datetime

def add_gigasecond(d):
    t = datetime.timedelta(seconds=10**9)
    return d+t
