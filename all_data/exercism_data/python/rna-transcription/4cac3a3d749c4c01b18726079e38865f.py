#
# to_rna function
#

def to_rna(dna):
    dna2rna = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    dna_list = []
    rna_list = []
    rna = ''
    for i in dna:
        dna_list.append(i)
    for i in dna_list:
        rna_list.append(dna2rna[i])
    rna = rna.join(rna_list)
    return rna
