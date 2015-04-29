def to_rna(DNA):
    """Converts DNA Strand to RNA.
       Given a DNA strand, its transcribed RNA strand is formed by replacing
       each nucleotide with its complement:
        * `G` -> `C`
        * `C` -> `G`
        * `T` -> `A`
        * `A` -> `U`"""

        
    return "".join(  ( {"G":"C",  "C":"G",  "T":"A", "A":"U"}[nuc] for nuc in DNA))

