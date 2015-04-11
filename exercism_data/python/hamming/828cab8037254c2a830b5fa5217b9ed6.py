def distance(s1, s2):
    # distance compares the strings s1 and s2 character by character,
    # incrementing count when discrepancy is found.
    count = 0
    for x in zip(s1, s2):
        if x[0] != x[1]:
            count += 1
    return count
