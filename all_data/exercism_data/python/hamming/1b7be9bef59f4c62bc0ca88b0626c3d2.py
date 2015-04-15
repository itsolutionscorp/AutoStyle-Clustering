def distance(a_strand, b_strand):
    return sum([ham_one(pair[0], pair[1]) for pair in zip(a_strand, b_strand)])


def ham_one(a, b):
    return 0 if a == b else 1
