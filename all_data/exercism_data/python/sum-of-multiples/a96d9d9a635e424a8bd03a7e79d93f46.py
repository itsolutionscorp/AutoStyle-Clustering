def sum_of_multiples(limit, multiples=None):
    if multiples is None:
        multiples = [3,5]
    elif multiples[0] == 0:
        multiples = multiples[1:]
    return sum(num for num in range(limit)
               if any(num % multiple == 0
               for multiple in multiples))
