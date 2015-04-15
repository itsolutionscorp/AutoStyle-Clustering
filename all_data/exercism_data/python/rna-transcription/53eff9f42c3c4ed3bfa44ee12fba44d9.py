def to_rna(DNA):
    RNA=''
    for x in DNA:
        if x == 'G':
            RNA += 'C'
        if x == 'C':
            RNA += 'G'
        if x == 'T':
            RNA += 'A'
        if x == 'A':
            RNA += 'U'
    return RNA
