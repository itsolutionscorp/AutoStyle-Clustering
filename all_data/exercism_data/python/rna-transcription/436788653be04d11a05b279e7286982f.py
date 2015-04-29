
def transcribe_a(nucleotides):
    return nucleotides.replace("A","u")
def transcribe_c(nucleotides):
    return nucleotides.replace("C","g")
def transcribe_g(nucleotides):
    return nucleotides.replace("G","c")
def transcribe_t(nucleotides):
    return nucleotides.replace("T","a")

def to_rna(dna):
    rna = transcribe_a(dna)
    rna = transcribe_c(rna)
    rna = transcribe_g(rna)
    rna = transcribe_t(rna)
    return rna.upper()
    
