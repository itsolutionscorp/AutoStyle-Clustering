import re

def hamming(strand1, strand2):

    normalized1 = _normalize(strand1)
    normalized2 = _normalize(strand2)

    min_len = min(len(normalized1), len(normalized2))
    max_len = max(len(normalized1), len(normalized2))
    
    hamm = max_len - min_len; 

    pos = 0
    while pos < min_len:
        if normalized1[pos] != normalized2[pos]:
            hamm += 1
        pos += 1

    return hamm

def _normalize(strand):
    normalized = strand.upper()
    normalized = re.sub('[^GCTA]', '', normalized)
    return normalized
