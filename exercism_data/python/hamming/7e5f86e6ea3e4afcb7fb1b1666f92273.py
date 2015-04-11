def distance(sequence1: str, sequence2: str) -> int:
    return sum([int(n1 != n2) for n1, n2 in zip(sequence1, sequence2)])
