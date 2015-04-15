from string import maketrans

def to_rna(dna):
    intrans, outrans = 'GCTA', 'CGAU'
    trantab = maketrans(intrans, outrans)
    return dna.translate(trantab)

if __name__ == "__main__":
    print to_rna('ACGTGGTCTTAA')
