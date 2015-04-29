def ismultiple(number, nlist):
    return (0 in [number % factor for factor in nlist if factor > 0])

def sum_of_multiples(limit, nlist = None):
    if nlist == None: nlist = [3,5]
    return sum([number for number in xrange(1,limit) if ismultiple(number, nlist)])
