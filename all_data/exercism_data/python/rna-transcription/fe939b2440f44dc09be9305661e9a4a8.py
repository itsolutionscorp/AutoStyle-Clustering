class Dna:

    #Correspondency from DNA to RNA chains
    DNA_RNA = {
        'G':'C',
        'C':'G',
        'T':'A',
        'A':'U'
    }

    def to_rna(self, dna):
        rna = ''
        dna = dna.rstrip()
        for chain in dna:
            rna += self.DNA_RNA[chain]
        return rna
        

def to_rna(str_dna):
    dna = Dna()
    return dna.to_rna(str_dna)
