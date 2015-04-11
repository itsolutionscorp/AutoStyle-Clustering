class DNAtoRNA:
    def __call__(self, nucleotide):
        return self.conv[nucleotide]
    def __init__(self):
        self.conv =  {'G' : 'C',
                      'C' : 'G',
                      'T' : 'A',
                      'A' : 'U'
                     }

def to_rna(strand):
    converter = DNAtoRNA()

    rna = ''
    for nucleotide in strand:
        rna += converter(nucleotide)

    return rna
