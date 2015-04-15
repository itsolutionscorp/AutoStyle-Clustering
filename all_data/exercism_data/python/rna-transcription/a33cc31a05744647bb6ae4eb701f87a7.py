from string import maketrans

def to_rna(dna):
    transcription = maketrans("GCTA", "CGAU")
    return dna.translate(transcription)
