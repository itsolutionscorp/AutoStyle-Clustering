def largest_palindrome(max_factor=None, min_factor=0):
    ans = 0
    factors = {}
    for i in reversed(xrange(min_factor, max_factor+1)):
        for j in reversed(xrange(i, max_factor+1)):
            x = i*j
            if str(x) == str(x)[::-1]:
                if x > ans:
                    ans = x
                    factors = {i, j}
    return ans, factors
    
def smallest_palindrome(max_factor=1, min_factor=0):
    ans = max_factor*min_factor
    factors = {}
    for i in xrange(min_factor, max_factor+1):
        for j in xrange(i, max_factor+1):
            x = i*j
            if str(x) == str(x)[::-1]:
                if x < ans:
                    ans = x
                    factors = {i, j}
    return ans, factors
