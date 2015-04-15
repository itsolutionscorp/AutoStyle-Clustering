def hamming(first, second):
    count = 0

    for base1, base2 in zip(first, second):
        if base1 != base2:
            count += 1

    """Account for difference in sequence lengths"""
    count += abs(len(first) - len(second))
    return count
