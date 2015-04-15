def sieve(limit):
    '''
    generates prime numbers up to a specified limit using
    the Sieve of Eratosthenes
    '''

    marked = []
    unmarked = []

    for i in range(2, limit + 1):
        if i not in marked:
            unmarked.append(i)
            marked += _compute_multiples(i, limit)

    return unmarked


def _compute_multiples(num, limit):
    return [num*i for i in range(2, limit / num + 1)]
