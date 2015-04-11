def distance(strand_a, strand_b):
    letter_pairs = zip(strand_a, strand_b)
    non_matching_letters = [p for p in letter_pairs if p[0] != p[1]]
    return len(non_matching_letters)
