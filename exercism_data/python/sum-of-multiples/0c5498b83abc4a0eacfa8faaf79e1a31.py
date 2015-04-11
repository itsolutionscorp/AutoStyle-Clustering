'''
sum_of_multiples.py

Find the sum of all the multiples of to integers
'''

def sum_of_multiples(n, ints=[3,5]):
    '''
    Find the sum of multiples below the given integer
    @param n: the limit
    @param ints: the list of integers to find multiples of, defaults to 3 and 5
    @returns: sum of the multiples of integers below n
    '''
    if len(ints) == 0:
        return 0

    mults = [0]
    for i in ints:
        if i == 0:
            continue
        mults.extend(range(i, n, i))
    return sum(list(set(mults)))
