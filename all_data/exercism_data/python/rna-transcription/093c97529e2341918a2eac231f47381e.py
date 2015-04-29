class DNA:
    def __init__(self,string):
        self.dna = string

    def to_rna(self):
        rna = []
        for index,item in enumerate(self.dna):
            if item == 'G':
                rna.append('C')
            elif item == 'C':
                rna.append('G')
            elif item == 'T':
                rna.append('A')
            elif item == 'A':
                rna.append('U')
            else:
                rna.append(item)
        return ''.join(rna)
