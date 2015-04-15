def nth_prime(n):
    count = 0
    currVal = 1
    while count < n:
        currVal += 1
        if is_prime(currVal):
            count += 1
    return currVal

def is_prime(x):
    if x == 1:
        return False
    for n in range(2, x/2 + 1):
        if x % n == 0:
            return False
    return True
