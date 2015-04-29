from functools import partial

dna_to_rna = {
        "G": "C",
        "C": "G",
        "T": "A",
        "A": "U",
        }

def replaceChars(replacement, chars):
    return "".join(replacement[c] for c in chars)

dnaToRna = partial(replaceChars, dna_to_rna)
