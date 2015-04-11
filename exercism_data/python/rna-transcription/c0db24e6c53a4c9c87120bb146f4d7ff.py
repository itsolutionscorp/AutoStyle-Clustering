def to_rna(dna):
    new_dna = ""
    for x in range(len(dna)):
        if dna[x] == 'A':
            new_dna+='U'
            continue
        if dna[x] == 'C':
            new_dna+='G'
            continue
        if dna[x] == 'G':
            new_dna+='C'
            continue
        if dna[x] == 'T':
            new_dna+='A'
            continue
        new_dna+=dna[x]
    dna = new_dna
    return dna
