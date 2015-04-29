def distance(first_strand, second_strand):
    if len(first_strand) != len(second_strand):
        return False
    i = 0
    for pair in zip(first_strand, second_strand):
        if len(set(pair)) > 1:
            i += 1
    return i
