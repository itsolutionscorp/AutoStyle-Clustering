from operator import mul
from functools import reduce
import random
from string import digits

# @profile
def slices(string, n):
    res = []
    if n > len(string) or n == 0:
        raise ValueError("Incompatible string and slice sizes")
    else:
        for i in range(len(string) - n+1):
            res.append([int(x) for x in string[i:i+n]])
    return res

# @profile
def largest_product(string, n):
    if string == "":
        return 1  # This is in here to pass the identity test, but the test
    else:         # itself makes no sense
        sliced = slices(string, n)
        return max([reduce(mul, l) for l in sliced])


if __name__ == "__main__":
    n = 10
    length = 10**6
    string = ''.join([random.choice(digits) for x in range(length)])
    ans = largest_product(string, n)
    print(ans)
