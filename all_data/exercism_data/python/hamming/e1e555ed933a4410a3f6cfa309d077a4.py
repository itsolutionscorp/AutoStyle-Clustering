def distance(s1, s2):
    """Returns the Hamming difference between two equal length DNA strands."""

    num_diffs = 0

    for i in range(0, len(s1)):
        if not s1[i] == s2[i]:
            num_diffs += 1

    return num_diffs
