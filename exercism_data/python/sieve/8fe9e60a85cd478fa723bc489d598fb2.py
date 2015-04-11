def sieve(n):
    # Initialize sequence of numbers; value of False => not prime.
    nums = {i: True for i in xrange(2,n+1)}
    
    for i in nums.iterkeys():
        if nums[i]:
            for j in xrange(2*i, n+1, i):
                nums[j] = False
        
    return [key for key,val in nums.iteritems() if val]
