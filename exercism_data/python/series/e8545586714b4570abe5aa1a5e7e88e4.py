def slices(sequence: list, size: int) -> list:
    if size > len(sequence):
        raise ValueError("size = %d must be shorter than length of sequence"
                         " (%d)" % (size, len(sequence)))
    elif size <= 0:
        raise ValueError("size must be > 0")

    sequence = list(map(lambda x: int(x), sequence))
    return [sequence[i:i+size] for i in range(len(sequence))
            if len(sequence[i:i+size]) == size]
