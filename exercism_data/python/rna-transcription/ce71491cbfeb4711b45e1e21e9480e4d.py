def to_rna(dnas):
    rna = ""
    for dna in dnas:
        rna += _TRANSCRIPTION_[dna]
    return rna

_TRANSCRIPTION_ = {
    'G' : 'C',
    'C' : 'G',
    'T' : 'A',
    'A' : 'U'
}
