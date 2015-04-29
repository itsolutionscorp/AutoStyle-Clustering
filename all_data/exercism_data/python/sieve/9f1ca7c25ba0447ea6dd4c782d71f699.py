def sieve(stop):
    nums = [True] * stop
    
    for i in xrange(2, stop/2):
        j = i*2
        while(j < stop):
            nums[j] = False
            j += i
    
    return [ i for i,flag in enumerate(nums) if flag and i >1 ]
