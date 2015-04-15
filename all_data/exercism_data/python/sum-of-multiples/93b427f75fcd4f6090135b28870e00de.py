def sum_of_multiples(max, what=[3, 5]):
    return sum(x for x in range(1, max) if [w for w in what if w != 0 and x % w == 0])
