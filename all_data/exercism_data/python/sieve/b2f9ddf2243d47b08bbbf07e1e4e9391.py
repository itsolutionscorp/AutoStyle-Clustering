#
# captainnurple's code for the Python Sieve exercise.
#
import math

# Algorithm approach:
# Create a list
# Start with 2. For every 2*i element zero it if 2*i <= upper bound
# Find next non-zero list element and repeat
# if element**2 is gt upper bound you're done
# Remove all zeroes and return your list

# sieve takes a single argument 'n' and returns all the prime numbers 
# from 2 to n
def sieve(n):
    # Create a comprehensive list of numbers from 0 to n
    masterList = range(n+1) # inclusive
    # Zero out element 1.
    masterList[1] = 0

    for i, value in enumerate(masterList):
        if value**2 > n: break # if element**2 is gt upper bound you're done

        if value != 0:
            # Zero every higher multiple of value
            rangeBound = int(math.floor(n/value)) # Calculate upper bound for multipliers
            multiples = range(2,rangeBound+1) # inclusive
            for j in multiples:
                masterList[j*i] = 0
    
    # Strip out zeroes
    masterList = [e for e in masterList if e != 0]

    return masterList
