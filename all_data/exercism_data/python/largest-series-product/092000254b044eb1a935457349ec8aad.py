import operator


def slices(sequence, slice):
    if slice > len(sequence):
        raise ValueError
    return [map(int, list(sequence[seq:seq+slice])) for seq in range(len(sequence) - slice + 1)]


def largest_product(sequence, slice):
    candidates = slices(sequence, slice)
    cand_vals = []
    for candidate in candidates:
        cand_vals.append(reduce(operator.mul, candidate, 1))
    return max(cand_vals)
