from string import maketrans

nucleotidesDNA = 'ACGT'
nucleotidesRNA = 'UGCA'
translate = maketrans(nucleotidesDNA, nucleotidesRNA)

def to_rna(dna):
    invalid = set([n for n in dna if not n in nucleotidesDNA])

    if invalid:
        raise ValueError('Nucleotides ' + ', '.join(invalid) + ' are not valid for DNA')

    return dna.translate(translate)
