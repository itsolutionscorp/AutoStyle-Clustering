def accumulate(iterable, fn):
    '''Return a new iterable containing
    the result of applying fn to each 
    element of the input iterable.
    '''
    return [ fn(e) for e in iterable ]
