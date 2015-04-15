def sum_of_multiples(n, factors=[3, 5]):
    """Return the sum of multiples of the given factors up to and including n"""

    def has_factors(k):
        """Return true if k is divisible by any of the factors"""
        return any(k % i == 0 for i in factors if i)

    return sum(filter(has_factors, range(1, n)))
