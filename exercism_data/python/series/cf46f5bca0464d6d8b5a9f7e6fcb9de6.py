def slices(s, size):
    if size == 0 or len(s) < size:
        raise ValueError("size too large")

    return [map(int, s[i:(i + size)]) for i in range(len(s) - size + 1)]
