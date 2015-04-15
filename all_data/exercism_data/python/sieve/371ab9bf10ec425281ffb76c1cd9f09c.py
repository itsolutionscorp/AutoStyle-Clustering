
def sieve(rng):
    group = range(2,rng)
    x=0
    while x < len(group):
        n=2
        while group[x]*n <= group[-1]:
            if group[x]*n in group:
                group.remove(group[x]*n)
            n+=1
        x+=1
    return group
            
            
        
