def sieve(limit):
    ''' uses the Sieve of Eratosthenes to find generate
        all primes from 2 up to a given number, exclusive.
        limit:   int > 2
        returns: list of int
    '''
    assert (type(limit) == int) and (limit > 2), "invalid limit value."

    numbers = range(2,limit)
    for number in numbers:
        multiples = getMultiples(number, limit)
        for multiple in multiples:
            if multiple in numbers:
                numbers.remove(multiple)

    return numbers

def getMultiples(number, limit):
    ''' returns a list of multiples of 'number' up to
        'limit', exclusive.
        number:  int > 0
        limit:   int > number*2
        returns: list of int
    '''
    if (limit//number < 2):
        return []
    else:
        return [number*multiplier for multiplier in range(2, (limit-1)//number + 1)]
