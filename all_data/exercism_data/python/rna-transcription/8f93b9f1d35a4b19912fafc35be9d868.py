COMPLEMENT = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}


def to_rna(dna_strand):
    try:
        return ''.join(COMPLEMENT[nucleotide] for nucleotide in dna_strand)
    except KeyError as e:
        raise ValueError('Invalid nucleotide "%s" in dna strand' % e.args[0])
