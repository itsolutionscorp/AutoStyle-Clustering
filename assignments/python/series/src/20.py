def slices(sequence, length):
    """
    slices(str, int) -> list of list of int
    Return a list of consecutive series with given length if each character in
    sequence is in "0123456789".
    """
    if length > len(sequence):
        raise ValueError("length must be lesser or equal to len(sequence)")
    elif length <= 0:
        raise ValueError("length must be greater than 0")
    sequence = [int(a) for a in sequence]
    return [sequence[start : start + length]
            for start in range(len(sequence) - length + 1)]
