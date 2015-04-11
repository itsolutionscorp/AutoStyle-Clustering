def distance(lhs, rhs):
    
    assert len(lhs) == len(rhs), "The length of both strings should be equal."
    
    count = 0
    for i in xrange(len(lhs)):
        count += lhs[i] != rhs[i]
        
    return count
