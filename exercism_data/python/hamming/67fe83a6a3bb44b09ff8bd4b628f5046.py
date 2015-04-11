"""Hamming exercise"""


def distance(sequence1, sequence2):
    """Calculate hamming distance"""

    assert len(sequence1) == len(sequence2), \
        "Unequal sequence length. Cannot calculate Hamming distance."

    hamming_distance = 0

    # seqarray vars not strictly necessary, but easier to read
    seqarray1 = list(sequence1)
    seqarray2 = list(sequence2)

    for i, c in enumerate(seqarray1):
        if seqarray1[i] != seqarray2[i]:
            hamming_distance += 1

    return hamming_distance
