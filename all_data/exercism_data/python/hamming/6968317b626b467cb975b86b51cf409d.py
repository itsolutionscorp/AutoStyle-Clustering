def distance(strand1, strand2):
    """Calculate the differences between two DNA strands."""

    if len(strand1) != len(strand2):
        # Sanity check to make sure strands are the same length
        return None

    difference = 0
    for i in range(len(strand1)):
        if strand1[i]  is not strand2[i]:
            difference += 1
    return difference
