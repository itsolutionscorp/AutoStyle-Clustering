def hamming(first, second):
    count = 0

    if len(first) > len(second):
        one = second
        two = first
    else:
        one = first
        two = second

    for index, base in enumerate(one):
        if base != two[index]:
            count += 1

    """Account for difference in sequence lengths"""
    count += abs(len(first) - len(second))
    return count
