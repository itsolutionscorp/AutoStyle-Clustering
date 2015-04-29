import re

def distance(strand1, strand2):

    strand1 = _normalize(strand1)
    strand2 = _normalize(strand2)

    min_len = min(len(strand1), len(strand2))
    max_len = max(len(strand1), len(strand2))

    hamm = max_len - min_len; 

    for pos in range(0, min_len):
        if strand1[pos] != strand2[pos]:
            hamm += 1

    return hamm

def _normalize(strand):
    normalized = strand.upper()
    normalized = re.sub('[^GCTA]', '', normalized)
    return normalized
