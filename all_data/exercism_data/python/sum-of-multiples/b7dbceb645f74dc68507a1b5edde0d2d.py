
def sum_of_multiples(top_bound, multiples=(3,5)):
    used_mults = []
    sum = 0
    for multiple in multiples:
        counter = 1
        if multiple > 0:
            while multiple*counter < top_bound:
                if multiple == multiples[0] or not multiple*counter in used_mults:
                    sum += multiple * counter
                    used_mults.append(multiple*counter)
                counter += 1
    return sum
