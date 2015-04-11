def distance(dna_a, dna_b):
    if len(dna_a) != len(dna_b):
        raise ValueError('Size Error')
        return
    cnt = 0
    for a, b in zip(dna_a, dna_b):
        if a != b:
            cnt += 1
    return cnt
