def sum_of_multiples(maxnum, numbers=[3,5]):
    return sum(sum(range(0, maxnum, n)) for n in numbers if n != 0)
