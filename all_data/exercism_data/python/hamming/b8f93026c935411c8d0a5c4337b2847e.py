def is_dna(sequence):
    return set(sequence) == set(("A", "C", "G", "T"))


def distance(seq1 : is_dna, seq2 : is_dna) -> int:
    assert len(seq1) == len(seq2)

    count = 0
    for x, y in zip(seq1, seq2):
        if x != y:
            count += 1

    return count
