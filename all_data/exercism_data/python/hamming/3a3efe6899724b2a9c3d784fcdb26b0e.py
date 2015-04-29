def distance( strand1, strand2 ):
    diff_count = 0
    if not strand1 == strand2:
        for index, nucleotide in enumerate( strand1 ):
            if nucleotide != strand2[ index ]:
                diff_count += 1
    return diff_count
