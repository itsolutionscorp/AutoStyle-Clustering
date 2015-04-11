def to_rna(dna):
    rna=''
    for i in range(len(dna)):
        if dna[i]=='G': rna+='C'
        if dna[i]=='C': rna+='G'
        if dna[i]=='T': rna+='A'
        if dna[i]=='A': rna+='U'
    return rna
