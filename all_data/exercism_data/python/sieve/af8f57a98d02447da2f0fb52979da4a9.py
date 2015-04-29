

def sieve(limit):

    prime = [i for i in range(2, limit + 1)]

    for div in range(2, limit + 1):
        prime = list(filter(lambda n : n <= div or bool(n % div), prime))
        
    return prime
