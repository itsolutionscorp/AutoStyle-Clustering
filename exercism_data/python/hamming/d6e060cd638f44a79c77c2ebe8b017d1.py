def distance(left,right):
    return sum([1 for (l,r) in zip(left, right) if l !=r ])
    
