class DNA(object):
    """
    Write a program that, given a DNA strand, returns its RNA complement (per RNA transcription).
    Both DNA and RNA strands are a sequence of nucleotides.
    The four nucleotides found in DNA are adenine (**A**), cytosine (**C**), guanine (**G**) and thymidine (**T**).
    The four nucleotides found in RNA are adenine (**A**), cytosine (**C**), guanine (**G**) and uracil (**U**).
    Given a DNA strand, its transcribed RNA strand is formed by replacing each nucleotide with its complement:

    * `G` -> `C`
    * `C` -> `G`
    * `T` -> `A`
    * `A` -> `U`
    """
    def __init__(self, initial_dna):
        self.initial_dna = initial_dna

    def to_rna(self):
        dna = self.initial_dna[:]
        rna = ""
        for n in dna:
            if n is 'G':
                rna+='C'
            if n is 'C':
                rna+='G'
            if n is 'T':
                rna+='A'
            if n is 'A':
                rna+='U'
        return rna
