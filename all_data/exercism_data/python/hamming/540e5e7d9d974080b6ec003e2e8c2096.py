import re

def distance(strand1, strand2):

    strand1 = _normalize(strand1)
    strand2 = _normalize(strand2)

    if (len(strand1) != len(strand2)):
        raise ValueError("The provided strands are not of equal length")

    hamm = sum([
        1 for pos in range(0, len(strand1))
        if strand1[pos] != strand2[pos]
    ])

    return hamm

def _normalize(strand):
    normalized = strand.upper()
    normalized = re.sub('[^GCTA]', '', normalized)
    return normalized
