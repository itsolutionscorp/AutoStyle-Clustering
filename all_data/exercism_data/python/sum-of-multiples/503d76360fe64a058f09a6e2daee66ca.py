def sum_of_multiples(top, factors=(3, 5)):
    # 0 is not a valid factor
    factor_set = set(factors) - {0}
    return sum(n for n in range(top) if any(n % d == 0 for d in factor_set))
