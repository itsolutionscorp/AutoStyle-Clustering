
def sieve(rng):
    group = range(2,rng)
    x=0
    while group[x]**2 < rng:
        n=group[x]
        while group[x]*n <= group[-1]:
            if group[x]*n in group:
                group.remove(group[x]*n)
            n += 1
        x+=1
    return group
            
            
        
