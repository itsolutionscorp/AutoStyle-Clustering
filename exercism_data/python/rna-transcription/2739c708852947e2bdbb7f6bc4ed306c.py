from string import maketrans
def to_rna(dna):
    tbl = maketrans('ATCG', 'UAGC')
    return dna.translate(tbl)
