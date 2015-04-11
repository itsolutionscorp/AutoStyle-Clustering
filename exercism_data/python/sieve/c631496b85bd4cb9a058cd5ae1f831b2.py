def sieve(stop):
    nums = [True] * stop
    nums[:2] = (False, False)
    
    for i in xrange(stop/2):
        if not nums[i]:
            continue
        
        for j in xrange(i*2, stop, i):
            nums[j] = False
    
    return [ i for i,flag in enumerate(nums) if flag ]
