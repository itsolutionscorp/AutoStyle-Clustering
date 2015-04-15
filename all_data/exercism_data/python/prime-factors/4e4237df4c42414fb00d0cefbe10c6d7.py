primes_under_1000 = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47,
                     53, 59, 61, 67, 71, 73,  79, 83, 89, 97, 101, 103, 107,
                     109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167,
                     173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229,
                     233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283,
                     293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359,
                     367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431,
                     433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491,
                     499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571,
                     577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641,
                     643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709,
                     719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787,
                     797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859,
                     863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941,
                     947, 953, 967, 971, 977, 983, 991, 997]

def prime_factors(n):
    """Find the prime factors for a given number"""

    #First, eliminate trivial cases
    if n == 1:
        return []
    if is_prime(n):
        return [int(n)]

    #Second, preprocess using stored block of primes
    prime_factors = []        
    for prime in primes_under_1000:
        if prime**2 > n:
            break
        while n % prime == 0:
            prime_factors.append(prime)
            n /= prime
        
    #Next check to see if there is anything left
    if n==1:
        return prime_factors
    if is_prime(n):
        prime_factors.append(int(n))
        return prime_factors

    #Finally, run pollard rho algorithm on any remainder
    prime_factors += pollard_rho(n)
    return sorted(prime_factors)
          
def pollard_rho(n, attempt_number=1):
    """Performs the pollard rho factorization of given number.
    If the factorization fails, increase the attempt number to
    alter the x value and then try again.
    """
    g = lambda i: (i**2+1) % n
    x = 2+(attempt_number-1)
    y = 2
    d = 1
    while d == 1:
        x = g(x)
        y = g(g(y))
        d = gcd(abs(x-y),n)
    if (d==n):
        return pollard_rho(n, attempt_number+1)
    else:
        return prime_factors(d) + prime_factors(n/d)        

def is_prime(n):
    """Checks to see if a number is prime"""
    if n <= 997:
        return n in primes_under_1000
    if n % 2 == 0 or n % 3 == 0:
        return False
    for i in range(5, int(n ** 0.5) + 1, 6):
        if n % i == 0 or n % (i + 2) == 0:
            return False
    return True

def gcd(a,b):
    """Returns the greatest common divisor of two given numbers"""
    if b == 0:
        return a
    else:
        return gcd(b, a % b)
