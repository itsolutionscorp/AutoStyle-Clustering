def sum_of_multiples(n, factors=[3,5]):
    if 0 in factors:
        factors.remove(0)

    def test_factor(i):
        for n in factors:
            if i % n == 0:
                return True
        return False
        
    multiples = filter(test_factor, range(1, n))
    return(sum(multiples))
