def distance(seq_one, seq_two):
    if len(seq_one) != len(seq_two) or not seq_one or not seq_two:
        return

    diff = 0
    for i, ntide in enumerate(seq_one):
        if seq_two[i] != ntide:
            diff += 1

    return diff
