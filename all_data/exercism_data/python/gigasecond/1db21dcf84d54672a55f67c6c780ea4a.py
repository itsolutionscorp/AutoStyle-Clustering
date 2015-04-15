from datetime import timedelta

def add_gigasecond(birthday):
    '''Compute the date of a person's 1 Gs anniversary, given their
    date of birth.'''

    return birthday + timedelta(seconds = 10**9)
    
