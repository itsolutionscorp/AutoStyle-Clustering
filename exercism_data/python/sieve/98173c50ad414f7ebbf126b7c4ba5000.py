def sieve(limit):
    # Handle a possible error condition.
    if limit < 2: raise ValueError('Limit must be 2 or greater.')

    # A list for primes, and a set of items we've marked (or excluded).
    primes_list = []
    marked_set = set()
    
    # Loop through the entire range of numbers to be examined.
    for current_num in range(2,limit+1):
        if current_num not in marked_set:
            # If the number isn't marked, add it to the Primes list. Also add
            # it to the Marked list, so we can ignore it on future passes.
            primes_list.append(current_num)
            marked_set.add(current_num)

        # As we move through the range, we're stepping by multiples of the 
        # current number. Multiples are added to the Marked set.
        for multiple in range (current_num,limit+1, current_num):
            marked_set.add(multiple)

    # Return the primes.
    return primes_list
