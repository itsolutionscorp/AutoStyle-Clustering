def sum_of_multiples(n, factors=(3, 5)):
    return sum(i for i in range(n)
               if any(i % factor == 0 for factor in factors if factor != 0))
