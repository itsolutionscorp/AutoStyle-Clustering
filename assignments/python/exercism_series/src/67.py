def slices(sequence, length):
    seqlength = len(sequence)
    if seqlength < length:
        raise ValueError("Sequence length is less than slice length.")
    if length < 1:
        raise ValueError("Slice length is less than 1.")
    return [[int(sequence[i + j]) for j in range(length)]
            for i in range(seqlength) if i + length <= seqlength]
