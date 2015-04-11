def slices(seq, n):
    N = len(seq)
    if not n or n > N:
        raise ValueError("n must be at least zero and no longer than seq.")
    return [map(int, seq[i:i + n]) for i in range(len(seq) - n + 1)]
