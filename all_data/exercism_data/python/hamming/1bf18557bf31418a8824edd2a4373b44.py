def distance(seq_a, seq_b):
    return sum(map(lambda a, b : a != b, seq_a, seq_b))
