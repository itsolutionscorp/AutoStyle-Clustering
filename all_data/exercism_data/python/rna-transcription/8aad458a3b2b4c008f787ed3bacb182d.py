class DNA():

    def __init__(self, dna_strand):
        self._strand = dna_strand

    def to_rna(self):
        strands_list = []
        for dna_char in self._strand:
            strands_list.append(self.__apply_translation(dna_char))
        return ''.join(strands_list)

    def __apply_translation(self, dna_char):
        if dna_char == "G":
            return "C"
        if dna_char == "C":
            return "G"
        if dna_char == "T":
            return "A"
        if dna_char == "A":
            return "U"
