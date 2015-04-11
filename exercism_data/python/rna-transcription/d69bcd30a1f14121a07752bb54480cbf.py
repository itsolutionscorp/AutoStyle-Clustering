rna_transcription = {'G':'C','C':'G','T':'A','A':'U'}

def to_rna(dna):
    ans = ''
    for nucleotide in dna:
        ans += rna_transcription[nucleotide]
    return ans
