
def slices(sequence, n):
    if n > len(sequence):
        raise ValueError("Subsequence larger than sequence")

    seq = [int(c) for c in sequence]

    return [seq[start : start + n]
            for start in range(len(seq) - n + 1)]

def largest_product(sequences, n):
    return max(product(seq) for seq in slices(sequences, n))

def product(seq):
    result = 1
    for n in seq:
        result *= n
    return result
