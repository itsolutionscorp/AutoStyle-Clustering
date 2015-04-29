def to_rna(dna):
    #replace Cs with placeholder %C to avoid changing new Cs
    return dna.replace('C', '%C').replace('G', 'C').replace('%C', 'G').replace('A', 'U').replace('T', 'A')
