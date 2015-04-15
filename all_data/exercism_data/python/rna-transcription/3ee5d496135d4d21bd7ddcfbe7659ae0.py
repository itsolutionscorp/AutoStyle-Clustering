# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`
def to_rna(dna):
    dna = dna.replace('A', 'U')
    dna = dna.replace('T', 'A')
    dna = dna.replace('G', 'g')
    dna = dna.replace('C', 'G')
    dna = dna.replace('g', 'C')
    return dna
