from datetime import date, timedelta

def meetup_day(year, month, weekday, which):
    '''Return date object of date in 'month' of 'year'
    that is a 'weekday', for which 'which' applies'''

    days_of_the_week = ('Monday', 'Tuesday', 'Wednesday',
                        'Thursday', 'Friday', 'Saturday', 'Sunday',)

    whiches = {
        '1st' : 0,   'first' : 0,
        '2nd' : 1,   'second' : 1,
        '3rd' : 2,   'third' : 2,
        '4th' : 3,   'fourth' : 3,
        '5th' : 4,   'last' : -1,
    }

    # Step 1: Get potential dates and append to list of candidates
    
    candidates = []        # list of potential dates

    # Start with first day of the month, ind the first 'weekday' of the month
    d = date(year, month, 1)
    while d.weekday() != days_of_the_week.index(weekday):
        d += timedelta(1)
    
    # Add all candidates from the month to the list
    while d.month == month:
        candidates.append(d)
        d += timedelta(7)

    # Step 2: Return the candidate that matches 'which'

    if which == 'teenth':
        if candidates[1].day > 12:
            return candidates[1]
        else:
            return candidates[2]
    else:
        return candidates[whiches[which]]
