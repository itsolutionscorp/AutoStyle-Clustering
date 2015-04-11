"""
Implementation of RNA transcription module

Specification:

Given a DNA strand, return its RNA complement (per RNA transcription).

Both DNA and RNA strands are a sequence of nucleotides.

The four nucleotides found in DNA are adenine (**A**), cytosine (**C**),
guanine (**G**) and thymidine (**T**).

The four nucleotides found in RNA are adenine (**A**), cytosine (**C**),
guanine (**G**) and uracil (**U**).

Given a DNA strand, its transcribed RNA strand is formed by replacing
each nucleotide with its complement:

* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`
"""

def to_rna(dna):
    """  transcribe a DNA string to RNA and return as another string """
    rna = []
    for nucleo in dna:
        if nucleo == 'G':
            rna.append('C')
        elif nucleo == 'C':
            rna.append('G')
        elif nucleo == 'T':
            rna.append('A')
        elif nucleo == 'A':
            rna.append('U')
        else:
            return 'Illegal nucleotide: ' + nucleo
    return ''.join(rna)
