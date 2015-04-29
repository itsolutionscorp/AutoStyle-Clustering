def hamming(original, mutation):

    # Make the sequences equal length.  Only one of them will be
    # padded and it will be padded with a non-nucleotide symbol (space),
    # so it will be safe to compare to a nucleotide in the other sequence.
    original = original.ljust(max(len(original), len(mutation)))
    mutation = mutation.ljust(max(len(original), len(mutation)))

    pairs = zip(original, mutation)
    return reduce(lambda distance, pair: distance + get_distance(pair),
                  pairs,
                  0)

def get_distance(pair):
    original = pair[0]
    mutation = pair[1]
    if original == mutation:
        return 0
    else:
        return 1
