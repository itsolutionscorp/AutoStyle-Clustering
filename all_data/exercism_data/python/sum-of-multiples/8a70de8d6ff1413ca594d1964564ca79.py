

def sum_of_multiples(top_bound, multiples=None):
    if multiples is None:
        multiples = [3,5]
    used_mults = []
    sum = 0
    for i in range(len(multiples)):
        counter = 1
        if multiples[i] > 0:
            while multiples[i]*counter < top_bound:
                if i > 0 and not multiples[i]*counter in used_mults:
                    sum += multiples[i] * counter
                    used_mults.append(multiples[i]*counter)
                counter += 1
    return sum
