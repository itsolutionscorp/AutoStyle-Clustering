def hamming(original, mutation):
    hamming_difference = 0
 
    if len(original) > len(mutation):
        mutation = mutation.ljust(len(original), ' ')
    elif len(mutation) > len(original):
        original = original.ljust(len(mutation), ' ')
 
    for (n1, n2) in zip(original, mutation):
        if n1 != n2:
            hamming_difference += 1

    return hamming_difference
