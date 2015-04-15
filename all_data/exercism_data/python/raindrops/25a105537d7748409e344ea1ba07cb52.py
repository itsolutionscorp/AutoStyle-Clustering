def raindrops(n):
    factors_and_drops = [(3, 'Pling'), (5, 'Plang'), (7, 'Plong')]
    drops = [drop
             for (factor, drop) in factors_and_drops
             if divisible_by(n, factor)]
    return ''.join(drops) if drops else str(n)


def divisible_by(n, f):
    return n % f == 0
