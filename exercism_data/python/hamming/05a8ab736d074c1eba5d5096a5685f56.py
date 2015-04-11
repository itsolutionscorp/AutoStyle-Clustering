def distance(*strands):
    # Input Validation
    assert len(strands)    == 2
    assert len(strands[0]) == len(strands[1])

    return sum(s1!=s2 for s1, s2 in zip(*strands))
