def sum_of_multiples(limit, multiples=[3,5]):
    return sum(x for x in xrange(limit) 
               if any(not x%y for y in multiples if y > 0))
               #any function marks the potential multiple True
