def sieve(lim):
    rng = range(2,lim+1)
    output = range(2,lim+1)
    
    for i in range(len(rng)):
        count = 0
        for j in range(len(output)):
            if output[count] != rng[i]:
                if not output[count] % rng[i]:
                    output.remove(output[count])
                    count -= 1 
            
            count += 1
            
    return output
