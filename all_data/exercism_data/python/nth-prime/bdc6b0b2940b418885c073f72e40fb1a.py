def nth_prime(n):
    ans = []
    i = 2
    
    while len(ans) < n:
        
        x = i
        
        for each in ans:
            if i % each == 0:
                x = None
                break
            
        if x:
            ans.append(x)
            
        i += 1
                
    return ans[-1]
