def sum_of_multiples(N, bases=None):
    if bases is None:
        bases = [3, 5]
    return sum(i for i in range(N) if any(b for b in bases if b and i % b == 0))
