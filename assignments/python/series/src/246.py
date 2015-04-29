def slices(sequence, size):
    if size > len(sequence):
        raise ValueError("size = %d must be shorter than length of sequence"
                         " (%d)" % (size, len(sequence)))
    elif size <= 0:
        raise ValueError("size must be > 0")
    sequence = list(map(int, sequence))
    return [sequence[i:i+size] for i in range(len(sequence) - size + 1)]
