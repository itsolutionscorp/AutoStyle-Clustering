def contains_factor(n, factors):
    # check if n has factor in factors
    return any([ (n % f == 0) for f in factors if f > 0])

def sum_of_multiples(n, factors=[3,5]):
    return sum([ i for i in range(1,n) if contains_factor(i,factors)])
