def slices(sequence, n):
    if n > len(sequence):
        raise ValueError("Subsequence larger than sequence")
    elif n <= 0:
        raise ValueError("Size too small")
    seq = list(map(int, sequence))
    return [seq[start : start + n]
            for start in range(len(seq) - n + 1)]
