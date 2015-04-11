rna_transcription_table = str.maketrans("GCTA", "CGAU")

def to_rna(dna):
    return str.translate(dna, rna_transcription_table)
