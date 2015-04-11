from collections import OrderedDict

# From Wikipedia:
# 1. Create a list of consecutive integers from 2 through n: (2, 3, 4, ..., n).
# 2. Initially, let p equal 2, the first prime number.
# 3. Starting from p, enumerate its multiples by counting to n in increments of p, and mark them in the list
#    (these will be 2p, 3p, 4p, etc.; the p itself should not be marked).
# 4. Find the first number greater than p in the list that is not marked. If there was no such number, stop.
#    Otherwise, let p now equal this new number (which is the next prime), and repeat from step 3.
# When the algorithm terminates, all the numbers in the list that are not marked are prime.
def sieve(n):
    # Validate the input
    if n is None or not isinstance(n, (int,long)):
        raise ValueError

    # 1. Consecutive integers from 2 through n
    # OrderedDict remembers the order in which the items were inserted.
    consecutive_nums = OrderedDict()
    for num in range(2,n+1):
        consecutive_nums[num] = False

    # 2. First prime number
    p = 2

    # Start from p
    while p <= n:

        # 3. Enumerate its multiples by counting to n in increments of p
        multiple = p * 2

        # Mark them in the list
        while multiple <= n:
            consecutive_nums[multiple] = True
            multiple += p

        # Skip p itself
        p += 1

        # 4. Find the first number greater than p in the list that is not marked.
        while p<=n and consecutive_nums[p]:
            p += 1

    # All the numbers in the list that are not marked are prime.
    return [prime for prime in consecutive_nums.keys() if not consecutive_nums[prime]]
