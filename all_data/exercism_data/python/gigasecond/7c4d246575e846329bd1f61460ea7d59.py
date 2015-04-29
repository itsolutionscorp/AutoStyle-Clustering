"""
    messed around with date object for a bit, was looking for to/from seconds
    since the epoch, but couldn't find it. I was going to write a function to
    add the days, and then adjust for years, and might do so for a second
    iteration of this particular problem, but then I found the to/from ordinal
    functions. After that it was a matter of conversions. Use the built-ins
    that you're given, right?
"""

SECONDS_IN_A_DAY = 24 * 60 * 60 # hours/day, minutes/hour, seconds/minute

GIGASECOND = 1000000000 #1,000,000,000

DAYS_IN_A_GIGASECOND = int(GIGASECOND/SECONDS_IN_A_DAY)

def add_gigasecond(date):
    return date.fromordinal(date.toordinal()+DAYS_IN_A_GIGASECOND)
