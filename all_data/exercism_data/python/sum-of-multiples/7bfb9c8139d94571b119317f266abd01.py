def sum_of_multiples(number, factors = None):
    list_of_numbers = list(range(1, number))
    set_of_multiples = set()

    for i in list_of_numbers:
        if factors:
            for j in factors:
                if j > 0 and i % j == 0:
                    set_of_multiples.add(i)
        else:
            if i % 3 == 0 or i % 5 == 0:
                set_of_multiples.add(i)

    return sum(i for i in set_of_multiples)
