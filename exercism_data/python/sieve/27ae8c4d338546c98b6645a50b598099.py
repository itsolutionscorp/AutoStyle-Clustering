#!/usr/bin/env python2
# -*- coding: utf-8 -*-

# NOTE: Please realize that the Sieve of Eratosthenes.
# NOTE: Is specifically about avoiding trial division.
# NOTE: We can make basic implict assumptions.
# NOTE: In trial division, we only ever need to search 1/2 the space.
# NOTE: That is to say:
#           if x = 27, we only need to search up to (x/2)-1
#           if x = 29, we only need to search up to (x/2)-1
# NOTE: 27/2=13.5, 29/2=14.5
#           2*13=26, 2*14=28, but 14 is even, so 14-1=13
#           2*14=28, 2*15=30, (too much)
#           so, 13.
# NOTE: Similarly, implicitly,
# NOTE: You only need to search up to 1/2
#           of sieved lists's remaining space.
# NOTE: On the same premise,
#           that any number greater than 1/2 of remaining,
#           is more than the multiple of largest remaining number.

def sieve(n):
    """Still not the explicit Sieve of Eratosthenes
    """

    # Immediately filters out x%2 > 2 and x%5 > 5
    nums = [x for x in range(2, n+1) if not (x > 2 and x % 2 == 0) and not (x > 5 and x % 5 == 0)]

    # nums[0] = 2, nums[1] = 3, so we start y=1
    # z starts at 1/2 the length of the remaining elements.
    # NOTE: We only ever need to check 1/2 of the search space.
    y, z = (1, len(nums)/2)

    while y < z:
        red = [x for x in range(nums[y]*2, max(nums)+1, nums[y])]
        nums = sorted(list(set(nums)-set(red)))
        y, z = (y+1, len(nums)/2)

    return nums

if __name__ == '__main__':
    print '\n\tThis script is not meant to be used this way.\n'
