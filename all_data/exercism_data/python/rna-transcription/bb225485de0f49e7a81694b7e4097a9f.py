__author__ = 'ronvis'

nucleotide_to_complement_dic = {
    'G':'G',
    'C':'C',
    'T':'U',
    'A':'A',
}

class DNA:
    def __init__(self, chain):
        self.chain = chain

    def to_rna(self):
        result = map(lambda x: nucleotide_to_complement_dic[x], self.chain)
        return ''.join(result)

