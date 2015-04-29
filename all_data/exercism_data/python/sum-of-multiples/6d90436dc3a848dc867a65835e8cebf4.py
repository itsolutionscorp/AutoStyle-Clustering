def sum_of_multiples(n, bases=[3,5]):
    all_multiples = []

    for base in bases:
        if base == 0: continue
        multiplier = 1
        multiple = multiplier * base
        while multiple < n:
            if multiple not in all_multiples:
                all_multiples.append(multiple)
            multiplier += 1
            multiple =  multiplier * base

    return sum(all_multiples)
