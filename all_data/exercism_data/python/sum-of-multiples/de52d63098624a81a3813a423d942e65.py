def is_multiple(n, multiples):
    return any(n % m == 0 for m in multiples if m != 0)

def sum_of_multiples(n, multiples=(3,5)):
    return sum(i for i in xrange(n) if is_multiple(i,multiples))
