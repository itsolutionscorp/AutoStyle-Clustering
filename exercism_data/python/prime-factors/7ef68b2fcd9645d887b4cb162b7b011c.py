def prime_factors(n):
    # Store our answer in this list
    ans = []

    # The first possible prime factor is 2
    i = 2
    
    # Iterate until we are at the final case where
    # our divisor 1
    while i <= n:
        
        # If i goes into n
        if n % i == 0:
            # Modify n and store i
            n = n / i
            ans.append(i)
            
        # Otherwise, move on to the next integer
        # (We don't have to worry about integers that
        #  aren't prime being introduced here since
        #  lesser multiples will already be taken care of
        #  before:
        #
        #  i.e. 4 won't ever be in the answer because
        #  2 will have been added until exhaustion.
        else: i += 1
        
    return ans
