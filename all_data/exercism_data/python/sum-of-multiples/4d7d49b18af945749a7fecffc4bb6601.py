def sum_of_multiples(n, factors = None):
    if factors is None:
        factors = [3, 5]
    return sum(set(multiple for f in factors if f != 0 # 0 is a vlid value but not as a factor
                            for multiple in range(0, n, f)))
