def distance(c_rna, rna):
    hd = 0
    i = 0
    for val in c_rna:
        if val != rna[i]:
            hd += 1
        i += 1
    return hd
