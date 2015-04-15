dna_to_rna_nucleotides = {
    'G':'C', 
    'C':'G', 
    'T':'A', 
    'A':'U' 
}

def to_rna(strand):
    rna = [];
    for x in range(0,len(strand)):
        rna.append(dna_to_rna_nucleotides.get(strand[x]));

    str_rna = "".join(rna);
    return str_rna;
