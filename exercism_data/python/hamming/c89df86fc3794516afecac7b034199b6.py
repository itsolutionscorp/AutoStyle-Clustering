def distance(strand1, strand2):
    pairs = zip(strand1,strand2)
    return sum(map(lambda pair: 0 if pair[0] == pair[1] else 1, pairs))
