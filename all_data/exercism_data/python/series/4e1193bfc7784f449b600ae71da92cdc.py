def slices(sequence, slice):
    if slice == 0 or slice > len(sequence):
        raise ValueError
    return [map(int, list(sequence[seq:seq+slice])) for seq in range(len(sequence) - slice + 1)]
