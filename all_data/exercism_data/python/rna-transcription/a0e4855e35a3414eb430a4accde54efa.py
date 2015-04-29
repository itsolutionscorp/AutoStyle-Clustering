transcription = {
    "A": "U",
    "C": "G",
    "G": "C",
    "T": "A"
}

def to_rna(dna):
    return "".join([
        transcription[nuc]
        for nuc in dna
    ])
