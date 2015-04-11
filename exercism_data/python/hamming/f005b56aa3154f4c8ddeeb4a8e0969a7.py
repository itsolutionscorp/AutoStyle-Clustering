def distance(strand_a, strand_b):
    return len([x for i, x in enumerate(strand_a) if strand_a[i] != strand_b[i]])


# EOF
