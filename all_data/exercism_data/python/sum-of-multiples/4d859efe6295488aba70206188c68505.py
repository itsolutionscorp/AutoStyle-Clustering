def sum_of_multiples(n, factors=[3,5]):
    def test_factor(i):
        for n in factors:
            if n == 0: continue
            if i % n == 0:
                return True
        return False
        
    multiples = filter(test_factor, range(1, n))
    return sum(multiples)
