def distance(strand_1, strand_2):
    validate(strand_1, strand_2)

    count = 0
    for i, c in enumerate(strand_1):
        if c != strand_2[i]:
            count += 1
    return count


def validate(strand_1, strand_2):
    if len(strand_1) != len(strand_2):
        raise ValueError("Strands must be equal length")
