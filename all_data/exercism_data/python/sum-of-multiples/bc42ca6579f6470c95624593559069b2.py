def sum_of_multiples(n, factors=[3,5]):
    if 0 in factors: factors.remove(0)
    return sum(filter(lambda i: any(i % num == 0 for num in factors),range(1,n)))
