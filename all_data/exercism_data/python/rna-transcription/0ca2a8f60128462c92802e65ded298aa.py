translate = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

def to_rna(dna):
    invalid = [n for n in dna if not n in translate]

    if len(invalid) > 0:
        raise InvalidNucleotideException(invalid)

    return ''.join([translate[n] for n in dna])

class InvalidNucleotideException(Exception):
    def __init(self, nucleotides):
        self.nucleotides = nucleotides

    def __str__(self):
        return 'Nucleotides ' + ', '.join(self.nucleotides) + ' are not valid for DNA'
