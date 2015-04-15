def sum_of_multiples(n, facs=[3, 5]):
    return sum(i for i in range(n) if any(not i % fac for fac in facs if fac))
