nuc_list = ['A', 'C', 'G', 'T', 'U']

class DNA(object):

    def __init__(self, dna):
        self.dna = dna

    def count(self, input_dna):
        num = 0

        try:
            assert input_dna in nuc_list
        except:
            raise ValueError('%s is not a nucleotide.' % input_dna)

        for letter in self.dna:
            if letter == input_dna:
                num += 1
        return num

    def nucleotide_counts(self):
        ret = {"A":0, "C":0, "G":0, "T":0}
        for dna in self.dna:
            ret[dna] += 1
        return ret
